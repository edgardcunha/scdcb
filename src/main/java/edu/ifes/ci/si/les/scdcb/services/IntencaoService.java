package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.RandomStringUtils;

import edu.ifes.ci.si.les.scdcb.model.Intencao;
import edu.ifes.ci.si.les.scdcb.model.Recebimento;
import edu.ifes.ci.si.les.scdcb.model.TipoDeCesta;
import edu.ifes.ci.si.les.scdcb.repositories.DoadorRepository;
import edu.ifes.ci.si.les.scdcb.repositories.IntencaoRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class IntencaoService {

    @Autowired
    private IntencaoRepository repository;
    @Autowired
    private DoadorRepository doadorRepository;

    public Intencao findById(Integer cdIntencao) {
    	try {
        	Intencao obj = repository.findById(cdIntencao).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + cdIntencao + ", Tipo: " + Intencao.class.getName());
        }
    }

    @Transactional
    public Collection<Intencao> findAll() {
        return repository.findAll();
    }

    public Intencao insert(Intencao obj) {
        try {
        	if(verificarRegrasDeNegocio(obj)) {
		    	obj.setCdIntencao(null);
                String generatedString = RandomStringUtils.randomAlphanumeric(8);
                obj.setCodigo(generatedString);
		        return repository.save(obj);
	        }
        } catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Filme não foi(foram) preenchido(s): Tipo de Filme, Diretor ou Participação");
		}
        return null;
    }

    public Intencao update(Intencao obj) {
        try {
        	findById(obj.getCdIntencao());
	        return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Filme não foi(foram) preenchido(s): Tipo de Filme, Diretor ou Participação");
		}
    }

    public void delete(Integer cdIntencao) {
        try {
            findById(cdIntencao);
            repository.deleteById(cdIntencao);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Intenção de Doar que possui Recebimentos associados!");
        }
    }

    public Collection<Intencao> findByTpCesta(TipoDeCesta tpCesta) {
        return repository.findByTpCesta(tpCesta);
    }
    
    // Implementando as regras de negócio relacionadas a manutenção de cadastro de intenção de doar
 	// Regra de Negócio 1: Um Doador não pode ter mais de três intenções de doar em aberto
    // Regra de Negócio 2: Uma Instituição não pode receber mais doações que o seu limite permitido
 	public boolean verificarRegrasDeNegocio(Intencao obj) {
 		boolean limiteIntencoes = false;
 		boolean limiteDoacoes = false;
 		
 		// Regra de Negócio 1: Um Doador não pode ter mais de três intenções de doar em aberto
 		if(obj.getDoador() != null)
 			if(repository.findTotalDeIntencoesByDoador(obj.getDoador().getCdUsuario()) < 3)
 				limiteIntencoes = true;
            else
                throw new BusinessRuleException("Não é possível efetuar sua intenção de doação, pois você já possui três ou mais intenções pendentes!");
 				
 		// Regra de Negócio 2: Uma Instituição não pode receber mais doações que o seu limite permitido
 	 	if(obj.getInstituicao() != null)
 	 		if(repository.findSaldoDeDoacoesByInstituicao(obj.getInstituicao().getCdUsuario()) < obj.getInstituicao().getLimiteDoacoes())
 	 			limiteDoacoes = true;
 	 		else
 	 			throw new BusinessRuleException("Esta instituição não está necessitando de cestas básicas no momento!");
 	 	
 		if(limiteIntencoes && limiteDoacoes)
 			return true;
 		
 		return false;
 	}

}

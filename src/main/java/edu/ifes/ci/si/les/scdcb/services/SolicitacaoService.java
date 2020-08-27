package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.RandomStringUtils;

import edu.ifes.ci.si.les.scdcb.model.Beneficiado;
import edu.ifes.ci.si.les.scdcb.model.Solicitacao;
import edu.ifes.ci.si.les.scdcb.model.enums.StatusSolicitacao;
import edu.ifes.ci.si.les.scdcb.repositories.BeneficiadoRepository;
import edu.ifes.ci.si.les.scdcb.repositories.SolicitacaoRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository repository;
    @Autowired
	private BeneficiadoRepository beneficiadoRepository;

    public Solicitacao findById(Integer id) {
    	try {
        	Solicitacao obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Solicitacao.class.getName());
        }
    }

    public Collection<Solicitacao> findAll() {
        return repository.findAll();
    }
    
    /*public Solicitacao findByBeneficiadoAndStatus(Integer cdBeneficiado, Integer status) {
        return repository.findByFitaAndStatus(cdBeneficiado, status);
    }*/

    public Solicitacao insert(Solicitacao obj) {
        try {
        	if(verificarRegrasDeNegocio(obj)) {
	        	obj.setCdSolicitacao(null);
                String generatedString = RandomStringUtils.randomAlphanumeric(8);
                obj.setCodigo(generatedString);
	        	return repository.save(obj);
        	}
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Solicitação não foi(foram) preenchido(s): Instituição, Tipo de Cesta ou Quantidade de Cestas");
        }
        return null;
    }

    public Solicitacao update(Solicitacao obj) {
        try {
            findById(obj.getCdSolicitacao());
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Solicitação não foi(foram) preenchido(s): Instituição, Tipo de Cesta ou Quantidade de Cestas");
        }
    }

    public void delete(Integer id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta Solicitação!");
        }
    }

    public Collection<Solicitacao> findAllByDtSolicitacao(LocalDate dtSolicitacao) {
        return repository.findAllByDtSolicitacao(dtSolicitacao);
    }
    
    // Implementando as regras de negócio relacionadas ao processo de negócio Solicitar Cesta Básica
 	// Regra de Negócio 1: O beneficiado não pode ter solicitacoes em aberto no mês corrente
 	public boolean verificarRegrasDeNegocio(Solicitacao obj) {
 		
 		// Regra de Negócio 1: O beneficiado não pode ter solicitacoes em aberto no mês corrente
 		Collection<Beneficiado> atendidos = beneficiadoRepository.findBeneficiadosAtendidos();
 		boolean beneficiadoLiberado = true;
 		for (Beneficiado atendido : atendidos) {
 			if (atendido.getCdUsuario() == obj.getBeneficiado().getCdUsuario()) {
 				beneficiadoLiberado = false;
 			}
 		}

 		if (!beneficiadoLiberado) {
 			throw new BusinessRuleException("O limite de doações mensais foi excedido!");
 		}

        if (beneficiadoLiberado) {
            return true;
        }
        
        return false;
 	}

}

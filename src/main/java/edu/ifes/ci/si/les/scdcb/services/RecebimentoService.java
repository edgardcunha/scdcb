package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scdcb.model.Recebimento;
import edu.ifes.ci.si.les.scdcb.model.enums.StatusIntencao;
import edu.ifes.ci.si.les.scdcb.repositories.IntencaoRepository;
import edu.ifes.ci.si.les.scdcb.repositories.RecebimentoRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class RecebimentoService {

    @Autowired
    private RecebimentoRepository recebimentoRepository;
    @Autowired
    private IntencaoRepository intencaoRepository;

    public Recebimento findById(Integer cdRecebimento) {
        try {
        	Recebimento obj = recebimentoRepository.findByCdRecebimento(cdRecebimento);
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + cdRecebimento);
        }
    }

    public Collection<Recebimento> findAll() {
        return recebimentoRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Recebimento insert(Recebimento obj) {
        try {
            if (verificarRegrasDeNegocio(obj)) {
                obj.setCdRecebimento(null);
                obj.getIntencao().setStatus(StatusIntencao.RECEBIDA);
                intencaoRepository.save(obj.getIntencao());
        	   return recebimentoRepository.save(obj);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Recebimento não foi(foram) preenchido(s): Doador, Instituição, Tipo e quantidade de Cesta Básica");
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Recebimento update(Recebimento obj) {
        try {
            if (verificarRegrasDeNegocio(obj)) {
                findById(obj.getCdRecebimento());
                obj.getIntencao().setStatus(StatusIntencao.RECEBIDA);
                intencaoRepository.save(obj.getIntencao());
        	   return recebimentoRepository.save(obj);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Recebimento não foi(foram) preenchido(s): Doador, Instituição, Tipo e quantidade de Cesta Básica");
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Integer cdRecebimento) {
        try {
            Recebimento obj = findById(cdRecebimento);
            obj.getIntencao().setStatus(StatusIntencao.ABERTA);
            intencaoRepository.save(obj.getIntencao());
            recebimentoRepository.delete(obj);
            recebimentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir este Recebimento!");
        }
    }

    /*public Collection<Recebimento> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino){
        return recebimentoRepository.findByClienteAndPeriodo(idCliente, inicio, termino);
    }

    public Collection<?> findQuantidadeDevolucaoClienteByPeriodo(String inicio, String termino){
        return recebimentoRepository.findQuantidadeDevolucaoClienteByPeriodo(inicio, termino);
    }*/

    // Implementando as regras de negócio relacionadas ao processo de negócio Receber Cesta Básica
    // Regra de Negócio 1: Não é possível receber a mesma cesta mais de uma vez
    public boolean verificarRegrasDeNegocio(Recebimento obj) {
        boolean doacaoPermitida = false;

        // Regra de Negócio 1: Não é possível receber a mesma cesta mais de uma vez
        if(obj.getIntencao() != null)
            if(intencaoRepository.findByCdIntencao(obj.getIntencao().getCdIntencao()).getStatus() == StatusIntencao.ABERTA)
                doacaoPermitida = true;
            else
                throw new BusinessRuleException("Esta Cesta Básica já foi recebida.");

        return doacaoPermitida;
    }

}

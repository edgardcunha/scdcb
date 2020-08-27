package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.TipoDeCesta;
import edu.ifes.ci.si.les.scdcb.repositories.TipoDeCestaRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class TipoDeCestaService {

    @Autowired
    private TipoDeCestaRepository repository;

    public TipoDeCesta findById(Integer id) {
    	try {
        	TipoDeCesta obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TipoDeCesta.class.getName());
        }
    }

    public Collection<TipoDeCesta> findAll() {
        return repository.findAll();
    }

    public TipoDeCesta insert(TipoDeCesta obj) {
        obj.setId(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Cesta não foi(foram) preenchido(s)");
        }
    }

    public TipoDeCesta update(TipoDeCesta obj) {
        findById(obj.getId());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Cesta não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Cesta com Doações vinculados a Intenção, Recebimento, Solicitação ou Entrega!");
        }
    }

    public Collection<TipoDeCesta> findMaioresPrecos(Double preco) {
        return repository.findMaioresPrecos(preco);
    }

}

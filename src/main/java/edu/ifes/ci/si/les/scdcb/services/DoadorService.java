package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.Doador;
import edu.ifes.ci.si.les.scdcb.repositories.DoadorRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository repository;

    public Doador findById(Integer id) {
        Doador obj = repository.findById(id).get();
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Doador.class.getName());
        }
        return obj;
    }

    public Collection<Doador> findAll() {
        return repository.findAll();
    }

    public Doador insert(Doador obj) {
    	obj.setCdUsuario(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Doador não foi(foram) preenchido(s)");
        }
    }

    public Doador update(Doador obj) {
    	findById(obj.getCdUsuario());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Doador não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Doador associado a uma Intenção de Doar!");
        }
    }

}

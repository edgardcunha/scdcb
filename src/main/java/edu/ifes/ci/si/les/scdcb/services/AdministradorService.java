package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.Administrador;
import edu.ifes.ci.si.les.scdcb.repositories.AdministradorRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public Administrador findById(Integer id) {
    	try {
        	Administrador obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Administrador.class.getName());
        }
    }

    public Collection<Administrador> findAll() {
        return repository.findAll();
    }

    public Administrador insert(Administrador obj) {
        obj.setCdUsuario(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Gerente não foi(foram) preenchido(s): Bairro");
        }
    }

    public Administrador update(Administrador obj) {
        findById(obj.getCdUsuario());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Gerente não foi(foram) preenchido(s): Bairro");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir este Gerente!");
        }
    }

}

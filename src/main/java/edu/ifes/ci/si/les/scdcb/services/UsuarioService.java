package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.Usuario;
import edu.ifes.ci.si.les.scdcb.repositories.UsuarioRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) {
        Usuario obj = repository.findById(id).get();
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
        }
        return obj;
    }

    public Collection<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario insert(Usuario obj) {
    	obj.setCdUsuario(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchido(s): Bairro ou Telefone");
        }
    }

    public Usuario update(Usuario obj) {
    	findById(obj.getCdUsuario());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchido(s): Bairro ou Telefone");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente associado a Reservas ou Empréstimos!");
        }
    }

}

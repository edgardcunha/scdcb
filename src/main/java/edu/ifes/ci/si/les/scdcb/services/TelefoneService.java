package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.Telefone;
import edu.ifes.ci.si.les.scdcb.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository repository;

    /*public Telefone findById(String numero) {
    	try {
        	Telefone obj = repository.findById(numero).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Número: " + numero + ", Tipo: " + Telefone.class.getName());
        }
    }*/

    public Collection<Telefone> findAll() {
        return repository.findAll();
    }
    
    /*public Collection<Telefone> findByDanificadaAndDisponivel(Boolean danificada, Boolean disponivel) {
        return repository.findByDanificadaAndDisponivel(danificada, disponivel);
    }*/

    public Telefone insert(Telefone obj) {
    	obj.setNumero(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Telefone não foi(foram) preenchido(s)");
        }
    }

    /*public Telefone update(Telefone obj) {
    	findById(obj.getNumero());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Telefone não foi(foram) preenchido(s)");
        }
    }*/

    /*public void delete(String numero) {
        findById(numero);
        try {
            repository.deleteById(numero);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Telefone vinculada a Itens de Empréstimos!");
        }
    }*/

    /*
    //Poderia ser findByUsuario 
    public Collection<Telefone> findByFilme(Telefone obj) {
        return repository.findByFilme(obj);
    }
    */

}

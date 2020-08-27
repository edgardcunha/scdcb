package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.Beneficiado;
import edu.ifes.ci.si.les.scdcb.repositories.BeneficiadoRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class BeneficiadoService {

    @Autowired
    private BeneficiadoRepository repository;

    public Beneficiado findById(Integer id) {
    	try {
        	Beneficiado obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Beneficiado.class.getName());
        }
    }

    public Collection<Beneficiado> findAll() {
        return repository.findAll();
    }

    public Beneficiado insert(Beneficiado obj) {
    	obj.setCdUsuario(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Funcionário não foi(foram) preenchido(s): Bairro");
        }
    }

    public Beneficiado update(Beneficiado obj) {
        findById(obj.getCdUsuario());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Funcionário não foi(foram) preenchido(s): Bairro");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir este Funcionário!");
        }
    }

    /*public Beneficiado findByLoginAndSenha(String login, String senha) {
        return repository.findByLoginAndSenha(login, senha);
    }*/
    
}

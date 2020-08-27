package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.Instituicao;
import edu.ifes.ci.si.les.scdcb.repositories.InstituicaoRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository repository;

    public Instituicao findById(final Integer id) {
        try {
        	Instituicao obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Instituicao.class.getName());
        }
    }    

    public Collection<Instituicao> findAll() {
        return repository.findAll();
    }

    public Instituicao insert(final Instituicao obj) {
    	obj.setCdUsuario(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Artista não foi(foram) preenchido(s)");
        }
    }

    public Instituicao update(final Instituicao obj) {
    	findById(obj.getCdUsuario());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Artista não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Artista com participações em Filmes!");
        }
    }

    public Collection<Instituicao> findAllByOrderByNome() {
        return repository.findAllByOrderByNome();
    }

    public Collection<Instituicao> findByNome(final String nome) {
        return repository.findByNome(nome);
    }
}

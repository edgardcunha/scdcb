package edu.ifes.ci.si.les.scdcb.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ifes.ci.si.les.scdcb.model.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {

    @Transactional(readOnly = true)
    public Collection<Instituicao> findAllByOrderByNome();

    @Transactional(readOnly = true)
    public Collection<Instituicao> findByNome(String nome);

}

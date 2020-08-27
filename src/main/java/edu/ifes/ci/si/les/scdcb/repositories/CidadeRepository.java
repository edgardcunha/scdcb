package edu.ifes.ci.si.les.scdcb.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ifes.ci.si.les.scdcb.model.Cidade;
import edu.ifes.ci.si.les.scdcb.model.UF;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Transactional(readOnly = true)
    public Collection<Cidade> findByUf(UF uf);

}

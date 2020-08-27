package edu.ifes.ci.si.les.scdcb.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scdcb.model.TipoDeCesta;

@Repository
public interface TipoDeCestaRepository extends JpaRepository<TipoDeCesta, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM TIPO_DE_FILME WHERE TIPO_DE_FILME.PRECO > ?1", nativeQuery = true)
    public Collection<TipoDeCesta> findMaioresPrecos(Double preco);

}

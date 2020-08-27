package edu.ifes.ci.si.les.scdcb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scdcb.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer> {

}

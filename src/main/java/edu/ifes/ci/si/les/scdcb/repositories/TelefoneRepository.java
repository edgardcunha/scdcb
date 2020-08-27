package edu.ifes.ci.si.les.scdcb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ifes.ci.si.les.scdcb.model.Telefone;


@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

	//Optional<Telefone> findById(String numero);

	//void deleteById(String numero);
	
}

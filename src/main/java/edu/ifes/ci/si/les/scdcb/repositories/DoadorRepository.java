package edu.ifes.ci.si.les.scdcb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ifes.ci.si.les.scdcb.model.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Integer> {

    /*@Transactional(readOnly = true)
    @Query(value = "SELECT CLIENTE.*  FROM EMPRESTIMO INNER JOIN CLIENTE ON EMPRESTIMO.CLIENTE_ID = CLIENTE.ID INNER JOIN MULTA ON EMPRESTIMO.ID = MULTA.EMPRESTIMO_ID WHERE MULTA.PAGO = 'FALSE'", nativeQuery = true)
    public Collection<Cliente> findDevedores();*/

}

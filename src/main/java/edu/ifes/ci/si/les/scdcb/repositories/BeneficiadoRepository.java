package edu.ifes.ci.si.les.scdcb.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scdcb.model.Beneficiado;

@Repository
public interface BeneficiadoRepository extends JpaRepository<Beneficiado, Integer> {

	//@Transactional(readOnly = true)
	//public Beneficiado findByLoginAndSenha(String login, String senha);

    //@Transactional(readOnly = true)
    //public Beneficiado findById();

    @Transactional(readOnly = true)
    @Query(value = "SELECT beneficiados.*, usuarios.* "
                   + "FROM solicitacoes "
                   + "INNER JOIN beneficiados ON solicitacoes.cd_beneficiado = beneficiados.cd_usuario "
                   + "LEFT OUTER JOIN usuarios ON beneficiados.cd_usuario = usuarios.cd_usuario "
                   + "WHERE solicitacoes.status = 0 "
                     + "AND solicitacoes.dt_solicitacao <= (CURDATE() - INTERVAL 1 MONTH);", nativeQuery = true)
    public Collection<Beneficiado> findBeneficiadosAtendidos();

}

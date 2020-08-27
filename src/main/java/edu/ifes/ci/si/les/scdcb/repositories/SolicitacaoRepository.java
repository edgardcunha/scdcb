package edu.ifes.ci.si.les.scdcb.repositories;

import java.util.Collection;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ifes.ci.si.les.scdcb.model.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {

    public Collection<Solicitacao> findAllByDtSolicitacao(LocalDate dtSolicitacao);
	
}

package edu.ifes.ci.si.les.scdcb.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ifes.ci.si.les.scdcb.model.Intencao;
import edu.ifes.ci.si.les.scdcb.model.TipoDeCesta;

@Repository
public interface IntencaoRepository extends JpaRepository<Intencao, Integer> {
    
    @Transactional(readOnly = true)
    public Collection<Intencao> findByTpCesta(TipoDeCesta tpCesta);

    @Transactional(readOnly = true)
    public Intencao findByCdIntencao(Integer cdIntencao);

	/*@Transactional(readOnly = true)
    @Query(value = "select * from RESERVA where RESERVA.FITA_ID = ?1 and STATUS = ?2", nativeQuery = true)
    public Reserva findByFitaAndStatus(Integer idFita, Integer status);
	
	@Transactional(readOnly = true)
    @Query(value = "select * from RESERVA where RESERVA.CLIENTE_ID = ?1 and RESERVA.DATA > ?2 and RESERVA.DATA < ?3", nativeQuery = true)
    public Collection<Reserva> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select CLIENTE.NOME as nome, count(RESERVA.ID) as quantidade from RESERVA inner join CLIENTE on RESERVA.CLIENTE_ID = CLIENTE.ID where DATA > ?1 and DATA < ?2 group by RESERVA.CLIENTE_ID", nativeQuery = true)
    public Collection<?> findQuantidadesReservasOfClientesByPeriodo(String inicio, String termino);*/

    @Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(intencoes.cd_intencao) "
                   + "FROM intencoes "
                   + "WHERE intencoes.cd_doador = ?1 "
                     + "AND intencoes.status = 0;", nativeQuery = true)
    public Integer findTotalDeIntencoesByDoador(Integer cdDoador);

    @Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(intencoes.cd_intencao) - COUNT(solicitacoes.cd_solicitacao) "
                   + "FROM intencoes "
                   + "INNER JOIN instituicoes ON intencoes.cd_instituicao = instituicoes.cd_usuario "
                   + "INNER JOIN solicitacoes ON intencoes.cd_instituicao = solicitacoes.cd_instituicao "
                   + "WHERE instituicoes.cd_usuario = ?1 "
                   + "  AND solicitacoes.status = 1 "
                   + "  AND intencoes.status = 1;", nativeQuery = true)
    public Integer findSaldoDeDoacoesByInstituicao(Integer cdInstituicao);

}
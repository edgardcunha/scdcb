package edu.ifes.ci.si.les.scdcb.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scdcb.model.Beneficiado;
import edu.ifes.ci.si.les.scdcb.model.Entrega;
import edu.ifes.ci.si.les.scdcb.model.enums.StatusSolicitacao;
import edu.ifes.ci.si.les.scdcb.repositories.BeneficiadoRepository;
import edu.ifes.ci.si.les.scdcb.repositories.EntregaRepository;
import edu.ifes.ci.si.les.scdcb.repositories.SolicitacaoRepository;
import edu.ifes.ci.si.les.scdcb.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ObjectNotFoundException;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private BeneficiadoRepository beneficiadoRepository;
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	public Entrega findByCdEntrega(Integer cdEntrega) {
		try {
        	Entrega obj = entregaRepository.findByCdEntrega(cdEntrega);
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + cdEntrega + ", Tipo: " + Entrega.class.getName());
        }
	}

	public Collection<Entrega> findAll() {
		return entregaRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
	public Entrega insert(Entrega obj) {
		try {
			if (verificarRegrasDeNegocio(obj)) {
				obj.setCdEntrega(null);
				obj.getSolicitacao().setStatus(StatusSolicitacao.ENTREGUE);
				solicitacaoRepository.save(obj.getSolicitacao());
				return entregaRepository.save(obj);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) da Entrega não foi(foram) preenchido(s): Beneficiado, Documento, Quantidade ou Tipo de Cesta");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
	public Entrega update(Entrega obj) {
		try {
			findByCdEntrega(obj.getCdEntrega());
			solicitacaoRepository.save(obj.getSolicitacao());
			return entregaRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Empréstimo não foi(foram) preenchido(s): Cliente ou Item de Empréstimo");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) //Todas as operações de persistência no BD serão realizadas em uma única transação (se tudo der certo commit, senão rollback em tudo).
	public void delete(Integer cdEntrega) {
		try {
			Entrega obj = findByCdEntrega(cdEntrega);
			obj.getSolicitacao().setStatus(StatusSolicitacao.ABERTA);
			solicitacaoRepository.save(obj.getSolicitacao());
			entregaRepository.delete(obj);
			entregaRepository.flush();  // Forçando a sincronização da(s) alteração(ões) e remoção neste momento (assim, diante de qualquer problema, o catch conseguirá capturar a exceção nesta camada de serviço) 
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Empréstimo que possui multas ou devoluções!");
		} 
	}

	// Implementando as regras de negócio relacionadas ao processo de negócio Entregar Cesta Básica
	// Regra de Negócio 1: A Instituição informa uma quantidade de cesta básica acima do limite familiar
	public boolean verificarRegrasDeNegocio(Entrega obj) {
		
		// Regra de Negócio 1: Cliente não pode ter multas não pagas
		Collection<Beneficiado> atendidos = beneficiadoRepository.findBeneficiadosAtendidos();
		boolean beneficiadoLiberado = true;
		for (Beneficiado atendido : atendidos) {
			if (atendido.getCdUsuario() == obj.getSolicitacao().getBeneficiado().getCdUsuario()) {
				beneficiadoLiberado = false;
			}
		}
		if (!beneficiadoLiberado) {
			throw new BusinessRuleException("O limite de doações mensais foi excedido!");
		}

		if (beneficiadoLiberado) {
			return true;
		} else {
			return false;
		}
	}

	/*public Collection<Entrega> findAllByBeneficiado(Integer cdBeneficiado) {
		Beneficiado beneficiado = beneficiadoRepository.findById(cdBeneficiado).get();
		return entregaRepository.findAllByBeneficiado(beneficiado);
	}*/

	/*public Collection<Emprestimo> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino) {
		return emprestimoRepository.findByClienteAndPeriodo(idCliente, inicio, termino);
	}

	public Collection<?> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(String inicio, String termino) {
		return emprestimoRepository.findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(inicio, termino);
	}

	public Collection<?> findQuantidadesEmprestimosOfBairrosByPeriodo(String inicio, String termino) {
		return emprestimoRepository.findQuantidadesEmprestimosOfBairrosByPeriodo(inicio, termino);
	}

	public Collection<?> findQuantidadesEmprestimosOfFilmesByPeriodo(String inicio, String termino) {
		return emprestimoRepository.findQuantidadesEmprestimosOfFilmesByPeriodo(inicio, termino);
	}

	public Collection<?> findTotaisAnoMes() {
		return emprestimoRepository.findTotaisAnoMes();
	}*/

}

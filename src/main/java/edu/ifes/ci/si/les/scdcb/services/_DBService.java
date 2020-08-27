package edu.ifes.ci.si.les.scdcb.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scdcb.model.*;
import edu.ifes.ci.si.les.scdcb.model.enums.*;
import edu.ifes.ci.si.les.scdcb.repositories.*;

@Service
public class _DBService {

	@Autowired
	private AdministradorRepository administradorRepository;
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private BeneficiadoRepository beneficiadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private DoadorRepository doadorRepository;
	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private IntencaoRepository intencaoRepository;
	@Autowired
	private RecebimentoRepository recebimentoRepository;
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private TipoDeCestaRepository tipoDeCestaRepository;
	@Autowired
	private UFRepository ufRepository;

	public void instantiateTestDatabase() throws ParseException, IOException {
		System.out.println("\n\n\n instantiateTestDatabase \n\n\n");
		final UF uf1 = new UF(null, "ES", "Espírito Santo");
		final UF uf2 = new UF(null, "MG", "Minas Gerais");
		final UF uf3 = new UF(null, "SP", "São Paulo");

		final Cidade ci1 = new Cidade(null, "Alegre", uf1);
		final Cidade ci2 = new Cidade(null, "Cachoeiro de Itapemirim", uf1);
		final Cidade ci3 = new Cidade(null, "Belo Horizonte", uf2);
		final Cidade ci4 = new Cidade(null, "Lavras", uf2);

		final Bairro ba1 = new Bairro(null, "Vila do Sul", ci1);
		final Bairro ba2 = new Bairro(null, "Guararema", ci1);
		final Bairro ba3 = new Bairro(null, "Maria Ortiz", ci2);
		final Bairro ba4 = new Bairro(null, "Centro", ci2);

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // poderia ser ("dd/MM/yyyy HH:mm");

		final Administrador ad1 = new Administrador(1, "Maria", "maria", "123");
		final Administrador ad2 = new Administrador(2, "Julia", "julia", "123");

		final Beneficiado be1 = new Beneficiado(2, 20, "Rua Eng. Laura Lane", ba1, "João", "joao", "123");
		final Beneficiado be2 = new Beneficiado(4, 40, "Rua Dr. Brício Mesquita", ba2, "José", "jose", "123");
		final Beneficiado be3 = new Beneficiado(10, 5, "Rua 25 de Março", ba4, "Ana Maria", "anamaria", "123");
		final Beneficiado be4 = new Beneficiado(7, 53, "Rua 25 de Março", ba4, "Flávia Helena", "flavia", "123");

		//final Telefone te1 = new Telefone("28 1111-2222");
		//final Telefone te2 = new Telefone("28 3333-4444");
		//final Telefone te3 = new Telefone("28 5555-6666");
		//final Telefone te4 = new Telefone("28 7777-8888");

		// be1.getTelefones().addAll(Arrays.asList(te1, te2));
		// be2.getTelefones().addAll(Arrays.asList(te3, te4));

		final Doador do1 = new Doador("111.111.111-11", sdf.parse("1990-01-01"), 101, "Rua Dr. Brício Mesquita", ba3,
				"Manoel", "manoel", "123");
		final Doador do2 = new Doador("222.222.222-22", sdf.parse("1990-01-02"), 102, "Rua Dr. Brício Mesquita", ba4,
				"Valeria", "valeria", "123");

		final Instituicao ins1 = new Instituicao("11.111.111/1111-11", 15, 21, "Rua Maria Madalena", ba3, "Instituicao 1", "inst1", "123");
		final Instituicao ins2 = new Instituicao("22.222.222/2222-11", 10, 35, "Rua Eng. Enésimo Moraes", ba3, "Instituicao 2", "inst2", "123");

		final TipoDeCesta tc1 = new TipoDeCesta(null, "Cesta A", "Cesta com 15 itens");
		final TipoDeCesta tc2 = new TipoDeCesta(null, "Cesta B", "Cesta com 20 itens");
		final TipoDeCesta tc3 = new TipoDeCesta(null, "Cesta C", "Cesta com 30 itens");

		final Intencao int1 = new Intencao(LocalDate.now(), 2, do1, tc1, ins1, StatusIntencao.ABERTA);
		final Intencao int2 = new Intencao(LocalDate.now(), 2, do2, tc2, ins2, StatusIntencao.ABERTA);

		int1.setStatus(StatusIntencao.RECEBIDA);
		final Recebimento re1 = new Recebimento(null, Instant.now(), 2, "nf1", int1);
		int2.setStatus(StatusIntencao.RECEBIDA);
		final Recebimento re2 = new Recebimento(null, Instant.now(), 2, "nf2", int2);

		final Solicitacao sol1 = new Solicitacao(LocalDate.now().minusMonths(2), 5, tc3, StatusSolicitacao.ABERTA, ins2, be4);
		final Solicitacao sol2 = new Solicitacao(LocalDate.now().minusMonths(2), 7, tc1, StatusSolicitacao.ABERTA, ins1, be3);
		final Solicitacao sol3 = new Solicitacao(LocalDate.now().minusMonths(1), 5, tc3, StatusSolicitacao.ABERTA, ins2, be2);
		final Solicitacao sol4 = new Solicitacao(LocalDate.now().minusMonths(1), 7, tc3, StatusSolicitacao.ABERTA, ins1, be1);

		sol1.setStatus(StatusSolicitacao.ENTREGUE);
		final Entrega en1 = new Entrega(Instant.now(), 2, "Documento do Beneficiado 1", sol1);
		sol2.setStatus(StatusSolicitacao.ENTREGUE);
		final Entrega en2 = new Entrega(Instant.now(), 2, "Documento do Beneficiado 2", sol2);

		// Trabalhando com o banco de dados utilizando os Repositories
		ufRepository.saveAll(Arrays.asList(uf1, uf2, uf3));
		cidadeRepository.saveAll(Arrays.asList(ci1, ci2, ci3, ci4));
		bairroRepository.saveAll(Arrays.asList(ba1, ba2, ba3, ba4));
		administradorRepository.saveAll(Arrays.asList(ad1, ad2));
		beneficiadoRepository.saveAll(Arrays.asList(be1, be2, be3, be4));
		doadorRepository.saveAll(Arrays.asList(do1, do2));
		instituicaoRepository.saveAll(Arrays.asList(ins1, ins2));
		tipoDeCestaRepository.saveAll(Arrays.asList(tc1, tc2, tc3));
		intencaoRepository.saveAll(Arrays.asList(int1, int2));
		recebimentoRepository.saveAll(Arrays.asList(re1, re2));
		solicitacaoRepository.saveAll(Arrays.asList(sol1, sol2, sol3, sol4));
		entregaRepository.saveAll(Arrays.asList(en1, en2));
	}
}

package edu.ifes.ci.si.les.scdcb.model.enums;

public enum StatusSolicitacao {

	ABERTA(0, "Solicitação de Cesta Básica em aberto"),
	ENTREGUE(1, "Solicitação de Cesta Básica entregue ao Beneficiado"),
	CANCELADA(2, "Solicitação de Cesta Básica cancelada");
	
	private int cod;
	private String descricao;
	
	private StatusSolicitacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusSolicitacao toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusSolicitacao x : StatusSolicitacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}

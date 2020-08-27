package edu.ifes.ci.si.les.scdcb.model.enums;

public enum StatusIntencao {

	ABERTA(0, "Intenção de Doar Cesta Básica em aberto"),
	RECEBIDA(1, "Intenção de Doar Cesta Básica recebida pela Instituição"),
	CANCELADA(2, "Intenção de Doar Cesta Básica cancelada");
	
	private int cod;
	private String descricao;
	
	private StatusIntencao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusIntencao toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusIntencao x : StatusIntencao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}

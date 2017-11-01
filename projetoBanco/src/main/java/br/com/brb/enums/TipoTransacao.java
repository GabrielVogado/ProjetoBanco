package br.com.brb.enums;

public enum TipoTransacao {
	
	DEBITO(" Débito"),
	CREDITO(" Crédito");
	
	private String descricao;
	
	TipoTransacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

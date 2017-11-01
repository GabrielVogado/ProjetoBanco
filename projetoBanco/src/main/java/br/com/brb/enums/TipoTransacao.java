package br.com.brb.enums;

public enum TipoTransacao {
	
	DEBITO(" D�bito"),
	CREDITO(" Cr�dito");
	
	private String descricao;
	
	TipoTransacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

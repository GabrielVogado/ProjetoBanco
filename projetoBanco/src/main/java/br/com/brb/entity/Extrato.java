package br.com.brb.entity;

import java.sql.Date;

public class Extrato {

	private Long id;
	private Long conta_id;
	private Date data;
	private Double valor;
	private char acao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConta_id() {
		return conta_id;
	}

	public void setConta_id(Long conta_id) {
		this.conta_id = conta_id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public char getAcao() {
		return acao;
	}

	public void setAcao(char acao) {
		this.acao = acao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}

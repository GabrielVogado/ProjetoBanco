package br.com.brb.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "extrato")
public class Extrato {

	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "conta_id")
	private Long conta_id;
	@Column(name = "data_hora")
	@Temporal(TemporalType.DATE)
	private Calendar data;
	@Column(name = "valor")
	private Double valor;
	@Column(name = "acao")
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
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
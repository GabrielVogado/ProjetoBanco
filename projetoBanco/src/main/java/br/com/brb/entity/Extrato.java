package br.com.brb.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "extrato")
public class Extrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "conta_id")
	private long contaId;
	
	@Column(name = "data_hora" ,columnDefinition="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "acao")
	private char acao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getContaId() {
		return contaId;
	}

	public void setContaId(long contaId) {
		this.contaId = contaId;
	}

}


package br.com.brb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Conta")
public class Poupança {

	@Id
	@Column
	private Double rendimento;

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}
}

package br.com.brb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Conta")
public class Especial {
	
	@Id
	@Column
	private Double limite;

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

}

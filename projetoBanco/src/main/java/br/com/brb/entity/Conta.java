package br.com.brb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconta")
	private Long num_conta;

	@Column(name = "saldo")
	private double saldo;

	@OneToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;


	@Column(name = "TIPO_CONTA")
	private String tipoConta;

	public Long getId() {
		return num_conta;
	}

	public void setId(Long id) {
		this.num_conta = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getNum_conta() {
		return num_conta;
	}

	public void setNum_conta(Long num_conta) {
		this.num_conta = num_conta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

}

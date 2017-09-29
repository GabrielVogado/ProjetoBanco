package br.com.brb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconta")
	private long id;

	@Column(name = "saldo")
	private double saldo;

	@OneToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy="conta", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Extrato> extratos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Extrato> getExtratos() {
		return extratos;
	}

	public void setExtratos(List<Extrato> extratos) {
		this.extratos = extratos;
	}

}

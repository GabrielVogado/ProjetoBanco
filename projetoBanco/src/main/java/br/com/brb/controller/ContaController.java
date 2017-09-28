package br.com.brb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IContaService;
//import br.com.brb.service.IUsuarioService;
//import br.com.brb.service.impl.ContaService;

//@ManagedBean(name = "contaController")
 @Named
@SessionScoped
public class ContaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
<<<<<<< Upstream, based on origin/master
//	private IUsuarioService usuarioService;
=======
	private IUsuarioService usuarioService;

	@Inject
>>>>>>> 9fb577f 
	private IContaService contaService;
<<<<<<< Upstream, based on origin/master
=======

>>>>>>> 9fb577f 
	private double vlrDeposito;
	private double vlrSaque;
<<<<<<< Upstream, based on origin/master
//	private double vlrTransferencia;
//
//	private String idUsuarioDestino;
=======
	private double vlrTransferencia;

	private String idUsuarioDestino;
>>>>>>> 9fb577f 

	public void depositaConta() {
<<<<<<< Upstream, based on origin/master
		//ContaService contaService = new ContaService();// **Após adicinar essa line : Transaction is required to perform
														// this operation (either use a transaction or extended
														// persistence context)
=======

>>>>>>> 9fb577f 
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();

		// if (conta == null) {
		// conta = new Conta();
		// }
		conta.setSaldo(conta.getSaldo() + this.vlrDeposito);

		conta.setUsuario(usuario);

<<<<<<< Upstream, based on origin/master
		usuario.setConta(contaService.deposita(conta));// Erro de NullPointer: contaService = null;
														//** erro :Transaction is required to perform this operation
														// (either use a transaction or extended persistence context)
		// usuario.setConta((Conta) ContaDao.getEm());
=======
		usuario.setConta(contaService.deposita(conta));

>>>>>>> 9fb577f 
	}

	public boolean saqueConta() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();

		if (conta == null || conta.getSaldo() < this.vlrSaque) {
			return false;
		}

		conta.setSaldo(conta.getSaldo() - this.vlrSaque);

		conta.setUsuario(usuario);

		usuario.setConta(contaService.saca(conta));

		return true;
	}

<<<<<<< Upstream, based on origin/master
//	public boolean transferenciaConta(String idUsuarioDestino) {
//		Usuario usuarioOrigem = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
//				.get("usuarioLogado");
//
//		Conta contaOrigem = usuarioOrigem.getConta();
//
//		if (contaOrigem.getSaldo() < this.vlrTransferencia)
//			return false;
////
////		// 1. Tem que criar um service pro
////		// usuario.
////		// 2. Na tela de transferencia tem
////		// que
////		// ter um campo pra pessoa
////		// digitar o Id do Usuario de
////		// Destino.
//		Usuario usuarioDestino = usuarioService.getUsuarioById(idUsuarioDestino); // Ele da erro a partir dessa Linha
//
//		if (usuarioDestino == null || usuarioDestino.getConta() == null)
//			return false;
//
//		Conta contaDestino = usuarioDestino.getConta();
//
//		contaDestino.setSaldo(contaDestino.getSaldo() + this.vlrTransferencia);
//		contaDestino.setUsuario(usuarioDestino);
//
//		contaOrigem.setSaldo(contaOrigem.getSaldo() - this.vlrTransferencia);
//		contaOrigem.setUsuario(usuarioOrigem);
//
//		usuarioOrigem.setConta(contaService.saca(contaOrigem));
//		usuarioDestino.setConta(contaService.deposita(contaDestino));
//
//		return true;
//	}
=======
	public boolean transferenciaConta() {
		Usuario usuarioOrigem = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");

		Conta contaOrigem = usuarioOrigem.getConta();

		if (contaOrigem.getSaldo() < this.vlrTransferencia)
			return false;

		Usuario usuarioDestino = usuarioService.getUsuarioById(Long.parseLong(idUsuarioDestino));
		System.out.println(usuarioDestino.getEmail());

		if (usuarioDestino == null || usuarioDestino.getConta() == null)
			return false;

		Conta contaDestino = usuarioDestino.getConta();

		contaDestino.setSaldo(contaDestino.getSaldo() + this.vlrTransferencia);
		contaDestino.setUsuario(usuarioDestino);

		contaOrigem.setSaldo(contaOrigem.getSaldo() - this.vlrTransferencia);
		contaOrigem.setUsuario(usuarioOrigem);

		usuarioOrigem.setConta(contaService.saca(contaOrigem));
		usuarioDestino.setConta(contaService.deposita(contaDestino));

		return true;
	}
>>>>>>> 9fb577f 

	public void extratoBancario() {

	}

	public double getVlrDeposito() {
		return vlrDeposito;
	}

	public void setVlrDeposito(double vlrDeposito) {
		this.vlrDeposito = vlrDeposito;
	}

	public double getVlrSaque() {
		return vlrSaque;
	}

	public void setVlrSaque(double vlrSaque) {
		this.vlrSaque = vlrSaque;
	}

<<<<<<< Upstream, based on origin/master
//	public double getVlrTransferencia() {
//		return vlrTransferencia;
//	}
//
//	public void setVlrTransferencia(double vlrTransferencia) {
//		this.vlrTransferencia = vlrTransferencia;
//	}
//
//	public void setIdUsuarioDestino(String usuarioId) {
//		this.idUsuarioDestino = usuarioId;
//	}
//
//	public String getIdUsuarioDestino() {
//		return this.idUsuarioDestino;
//	}
=======
	public double getVlrTransferencia() {
		return vlrTransferencia;
	}

	public void setVlrTransferencia(double vlrTransferencia) {
		this.vlrTransferencia = vlrTransferencia;
	}

	public void setIdUsuarioDestino(String usuarioId) {
		this.idUsuarioDestino = usuarioId;
	}

	public String getIdUsuarioDestino() {
		return this.idUsuarioDestino;
	}
>>>>>>> 9fb577f 
}

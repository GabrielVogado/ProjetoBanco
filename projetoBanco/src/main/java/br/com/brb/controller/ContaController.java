package br.com.brb.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IContaService;
import br.com.brb.service.IUsuarioService;

@ManagedBean(name = "contaController")
@SessionScoped
public class ContaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IUsuarioService usuarioService;
	
	@Inject
	private IContaService contaService;
	
	
	private double vlrDeposito;
	private double vlrSaque;
	private double vlrTransferencia;
	
	private String idUsuarioDestino;

	public void depositaConta() {
		// ContaService contaService = new ContaService();// **Ap�s adicinar essa line :
		// Transaction is required to perform
		// this operation (either use a transaction or extended
		// persistence context)
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();

		if (conta == null) {
			conta = new Conta();// Essa Linha N�o � Executada
		}
		/* CONTASERVICE ESTA NULA!!!!! */
		conta.setSaldo(conta.getSaldo() + this.vlrDeposito);

		conta.setUsuario(usuario);

		usuario.setConta(contaService.deposita(conta));// Erro de NullPointer: contaService = null;
														// ** erro :Transaction is required to perform this operation
														// (either use a transaction or extended persistence context)
		// usuario.setConta((Conta) ContaDao.getEm());
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

		usuario.setConta(contaService.saca(conta));// Erro de NullPointer: contaService = null;

		return true;
	}

	public boolean transferenciaConta() {
	// Usuario usuarioOrigem = (Usuario)
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .get("usuarioLogado");
	//
	// Conta contaOrigem = usuarioOrigem.getConta();
	//
	// if (contaOrigem.getSaldo() < this.vlrTransferencia)
	// return false;
	////
	//// // 1. Tem que criar um service pro
	//// // usuario.
	//// // 2. Na tela de transferencia tem
	//// // que
	//// // ter um campo pra pessoa
	//// // digitar o Id do Usuario de
	//// // Destino.
	Usuario usuarioDestino = usuarioService.getUsuarioById( Long.parseLong( idUsuarioDestino ) );
	System.out.println(  usuarioDestino.getEmail() );
	// Ele da erro a partir dessa Linha
	//
	// if (usuarioDestino == null || usuarioDestino.getConta() == null)
	// return false;
	//
	// Conta contaDestino = usuarioDestino.getConta();
	//
	// contaDestino.setSaldo(contaDestino.getSaldo() + this.vlrTransferencia);
	// contaDestino.setUsuario(usuarioDestino);
	//
	// contaOrigem.setSaldo(contaOrigem.getSaldo() - this.vlrTransferencia);
	// contaOrigem.setUsuario(usuarioOrigem);
	//
	// usuarioOrigem.setConta(contaService.saca(contaOrigem));
	// usuarioDestino.setConta(contaService.deposita(contaDestino));
	//
		return true;
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
}

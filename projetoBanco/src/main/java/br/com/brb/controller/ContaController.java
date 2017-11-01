package br.com.brb.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Extrato;
import br.com.brb.entity.Usuario;
import br.com.brb.exception.NegocioException;
import br.com.brb.service.IContaService;
import br.com.brb.service.IExtratoService;

@ManagedBean(name = "contaController")
@SessionScoped
public class ContaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IExtratoService extractService;

	@Inject
	private IContaService contaService;

	private double vlrDeposito;
	private double vlrSaque;
	private double vlrTransferencia;
	private double saldo;
	private Long idUsuarioDestino;
	private List<Extrato> extratoConta;

	public void init() {
		setSaldo(0);
		setVlrDeposito(0);
		setVlrSaque(0);
		setVlrTransferencia(0);
		setIdUsuarioDestino(new Long(0));
	}

	public List<Conta> getlistConta() {

		try {
			return contaService.getList();

		} catch (NegocioException e) {
			e.printStackTrace();
			return null;
		}
	}

	public double mostraSaldo() {
		Usuario usuario = getUsuarioSessao();
		Conta conta = usuario.getConta();
		return conta.getSaldo();
	}

	public void realizarTransferencia() {

		try {
			contaService.realizarTransferencia(getUsuarioSessao(), idUsuarioDestino, vlrTransferencia);
			addInfoMensage("Transferência realizada com Sucesso!");
			// Limpa dos dados do formulário
			init();

		} catch (NegocioException e) {
			addErroMensage(e.getMessage());
		}
		this.vlrTransferencia = 0;
		this.idUsuarioDestino = 0L;
	}

	@SuppressWarnings("unused")
	private void gravaExtrato(long contaId, Double valor, String acao) {
		Extrato extrato = new Extrato();
		extrato.setAcao(acao);
		extrato.setData(new Date());
		extrato.setValor(valor);
		extrato.setContaId(contaId);
		extractService.gravarDados(extrato);
	}

	public void getExtratoConta(Conta conta) {
		setExtratoConta(extractService.getExtrato(conta.getId()) );
	}
	
	public List<Extrato> getlistaExtrato() {
		Usuario usuario = getUsuarioSessao();
		Conta conta = usuario.getConta();
		return extractService.getExtrato(conta.getId());

	}

	public void depositaConta() {

		try {
			contaService.realizarDeposito(getUsuarioSessao(), vlrDeposito);
			addInfoMensage("Deposito relizado com sucesso");

		} catch (NegocioException e) {
			addErroMensage(e.getMessage());
		}
	
		this.vlrDeposito = 0;
	}

	public void realizarSaque() {

		try {
			contaService.realizarSaque(getUsuarioSessao(), vlrSaque);
			addInfoMensage("Saque realizado com sucesso.");

		} catch (NegocioException e) {
			addErroMensage(e.getMessage());
		}
		this.vlrSaque = 0;
	}

	private void addInfoMensage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}

	private void addErroMensage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}

	private Usuario getUsuarioSessao() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
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

	public void setIdUsuarioDestino(Long usuarioId) {
		this.idUsuarioDestino = usuarioId;
	}

	public Long getIdUsuarioDestino() {
		return this.idUsuarioDestino;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return getUsuarioSessao();
	}

	public List<Extrato> getExtratoConta() {
		return extratoConta;
	}

	public void setExtratoConta(List<Extrato> extratoConta) {
		this.extratoConta = extratoConta;
	}
}
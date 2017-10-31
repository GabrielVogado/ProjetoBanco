package br.com.brb.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.brb.constante.ValorConstante;
import br.com.brb.entity.Conta;
import br.com.brb.entity.Extrato;
import br.com.brb.entity.Usuario;
import br.com.brb.exception.NegocioException;
import br.com.brb.exception.ValorDepositoMenorQueZeroException;
import br.com.brb.exception.ValorDepositoNaoPodeZeroException;
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

	public void init() {
		setSaldo(new Double(0));
		setVlrDeposito(new Double(0));
		setVlrSaque(new Double(0));
		setVlrTransferencia(new Double(0));
	}

	public List<Conta> getlistConta() {

		try {
			return contaService.getList();

		} catch (NegocioException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Extrato> getlistaExtrato() {
		Usuario usuario = getUsuarioSessao();
		Conta conta = usuario.getConta();
		return extractService.getExtrato(conta.getId());

	}

	public void depositaConta() {

		Usuario usuario = getUsuarioSessao();
		Conta conta = usuario.getConta();

		try {
			ValorDepositoNaoPodeZeroExceptio(getVlrDeposito());
			validarValorDepositoMenorQueZero(getVlrDeposito());

			conta.setSaldo(conta.getSaldo() + this.vlrDeposito);
			conta.setUsuario(usuario);
			usuario.setConta(contaService.deposita(conta));
			gravaExtrato(conta.getId(), this.vlrDeposito, "Credito");

			addInfoMensage("Deposito Realizado Com Sucesso =)");

		} catch (NegocioException e) {
			addErroMensage(e.getMessage());
		}
	}

	private void ValorDepositoNaoPodeZeroExceptio(double vlrDeposito) throws ValorDepositoNaoPodeZeroException {

		if (vlrDeposito == ValorConstante.ZERO) {
			throw new ValorDepositoNaoPodeZeroException();
		}
	}

	private void validarValorDepositoMenorQueZero(double vlrDeposito) throws ValorDepositoMenorQueZeroException {

		if (vlrDeposito < ValorConstante.ZERO) {
			throw new ValorDepositoMenorQueZeroException();
		}
	}

	public void realizarSaque() {

		try {
			contaService.realizarSaque(getUsuarioSessao(), vlrSaque);
			addInfoMensage("Saque realizado com exito.");

		} catch (NegocioException e) {
			addErroMensage(e.getMessage());
		}
	}

	private void addInfoMensage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}

	private void addErroMensage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}

	private Usuario getUsuarioSessao() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
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
	}

	private void gravaExtrato(long contaId, Double valor, String acao) {
		Extrato extrato = new Extrato();
		extrato.setAcao(acao);
		extrato.setData(new Date());
		extrato.setValor(valor);
		extrato.setContaId(contaId);
		extractService.gravarDados(extrato);
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
}
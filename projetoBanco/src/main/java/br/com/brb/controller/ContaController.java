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
import br.com.brb.exception.ContaNaoPodeSerNulaException;
import br.com.brb.exception.UsuarioNaoPodeNuloException;
import br.com.brb.exception.ValorDepositoMenorQueZeroException;
import br.com.brb.exception.ValorDepositoNaoPodeZeroException;
import br.com.brb.exception.ValorSaqueMaiorQueSaldoException;
import br.com.brb.exception.ValorSaqueMenorQueZeroException;
import br.com.brb.exception.ValorTransferenciaMaiorQueSaldoException;
import br.com.brb.exception.ValorTransferenciaNaoPodeZeroException;
import br.com.brb.service.IContaService;
import br.com.brb.service.IExtratoService;
import br.com.brb.service.IUsuarioService;

@ManagedBean(name = "contaController")
@SessionScoped
public class ContaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IExtratoService extractService;

	@Inject
	private IUsuarioService usuarioService;

	@Inject
	private IContaService contaService;

	private double vlrDeposito;
	private double vlrSaque;
	private double vlrTransferencia;
	private double saldo;
	private Long idUsuarioDestino;
	private Double rendimento = 0.005;
	private Double limite = 200.00;
	@SuppressWarnings("unused")
	private Usuario usuario;
	private Double ZERO = 0.0;

	public void init() {
		setSaldo(new Double(0));
		setVlrDeposito(new Double(0));
		setVlrSaque(new Double(0));
		setVlrTransferencia(new Double(0));
	}

	public List<Conta> getlistConta() {
		return contaService.getList();
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

		} catch (ValorDepositoNaoPodeZeroException e) {
			addErroMensage("Por Favor,Insira um Valor Maior Que Zero");

		} catch (ValorDepositoMenorQueZeroException e) {
			addErroMensage("Por favor,Insira Um Valor Positivo");
		}
	}

	private void ValorDepositoNaoPodeZeroExceptio(double vlrDeposito) throws ValorDepositoNaoPodeZeroException {
		if (vlrDeposito == ZERO) {

			throw new ValorDepositoNaoPodeZeroException();
		}

	}

	private void validarValorDepositoMenorQueZero(double vlrDeposito) throws ValorDepositoMenorQueZeroException {
		if (vlrDeposito < ZERO) {

			throw new ValorDepositoMenorQueZeroException();
		}
	}

	public void realizarSaque() {

		Usuario usuario = getUsuarioSessao();
		Conta conta = usuario.getConta();

		try {
			validarValorSaqueMaiorQueSaldo(conta);
			validarValorSaqueMenorQueZero(getVlrSaque());

			conta.setSaldo(conta.getSaldo() - vlrSaque);
			conta.setUsuario(usuario);
			usuario.setConta(contaService.saca(conta));
			gravaExtrato(conta.getId(), vlrSaque * (-1), "Debito");

			addInfoMensage("Saque realizado com exito.");

		} catch (ValorSaqueMenorQueZeroException e) {
			addErroMensage("O valor informado é menor ou igual a 0 .");

		} catch (ValorSaqueMaiorQueSaldoException e) {
			addErroMensage("O valor informado para o saque é menor que o valor de saldo atual.");
		}
	}

	private void validarValorSaqueMaiorQueSaldo(Conta conta) throws ValorSaqueMaiorQueSaldoException {
		if (conta.getSaldo() < this.vlrSaque) {
			throw new ValorSaqueMaiorQueSaldoException();
		}
	}

	private void validarValorSaqueMenorQueZero(double vlrSaque) throws ValorSaqueMenorQueZeroException {
		if (vlrSaque < ZERO || vlrSaque == ZERO) {
			throw new ValorSaqueMenorQueZeroException();
		}
	}

	private void addInfoMensage(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
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

	// Método de Transferencia aceita null;
	public void transferenciaConta() {
		Usuario usuarioOrigem = getUsuarioSessao();
		Conta contaOrigem = usuarioOrigem.getConta();
		Usuario usuarioDestino = usuarioService.getUsuarioById((idUsuarioDestino));
		Conta contaDestino = usuarioDestino.getConta();// valor null

		try {
			ContaNaoPodeSerNula(usuarioDestino);
			UsuarioNaoPodeNulo(usuarioDestino);
			ValorTranseferenciaMaiorQueSaldo(contaOrigem);
			ValorTransferenciaNaoPodeZero();

			contaDestino.setSaldo(contaDestino.getSaldo() + this.vlrTransferencia);
			contaDestino.setUsuario(usuarioDestino);
			contaOrigem.setSaldo(contaOrigem.getSaldo() - this.vlrTransferencia);
			contaOrigem.setUsuario(usuarioOrigem);
			usuarioOrigem.setConta(contaService.saca(contaOrigem));
			usuarioDestino.setConta(contaService.deposita(contaDestino));
			gravaExtrato(contaOrigem.getId(), this.vlrTransferencia * (-1), " Transferencia Debito");
			gravaExtrato(contaDestino.getId(), this.vlrTransferencia, "Transferencia Credito");

			addInfoMensage("Transferencia realizada com Sucesso ");

		} catch (ContaNaoPodeSerNulaException e) {
			addErroMensage("Conta não encontrada ou não existe,verifique se Conta é válida");
		} catch (UsuarioNaoPodeNuloException e) {
			addErroMensage("Usuario Não Encontrado,ou Não existe,Verifique se Usuario esta correto..");
		} catch (ValorTransferenciaNaoPodeZeroException e) {
			addErroMensage(
					"Valor de Transferencia é igual a Zero ou Valor é Negativo ,por favor,insira um valor maior que zero");
		} catch (ValorTransferenciaMaiorQueSaldoException e) {
			addErroMensage(
					"Saldo Insuficiente para Transferencia,insira um Valor menor ou entre em contato com o Gerente do seu banco");
		}
	}

	private void ValorTransferenciaNaoPodeZero() throws ValorTransferenciaNaoPodeZeroException {
		if (vlrTransferencia < ZERO || vlrTransferencia == ZERO) {

			throw new ValorTransferenciaNaoPodeZeroException();

		}

	}

	private void ValorTranseferenciaMaiorQueSaldo(Conta contaOrigem) throws ValorTransferenciaMaiorQueSaldoException {
		if ((contaOrigem.getSaldo() < this.vlrTransferencia)) {

			throw new ValorTransferenciaMaiorQueSaldoException();
		}

	}

	private void UsuarioNaoPodeNulo(Usuario usuarioDestino) throws UsuarioNaoPodeNuloException {
		if (usuarioDestino == null) {

			throw new UsuarioNaoPodeNuloException();
		}

	}

	private void ContaNaoPodeSerNula(Usuario usuarioDestino) throws ContaNaoPodeSerNulaException {
		if (usuarioDestino.getConta() == null) {

			throw new ContaNaoPodeSerNulaException();
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

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public Usuario getUsuario() {
		return getUsuarioSessao();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
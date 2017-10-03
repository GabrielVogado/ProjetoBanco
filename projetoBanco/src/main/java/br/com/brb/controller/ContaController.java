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
	private String idUsuarioDestino;

	
	public List<Extrato> getlistaExtrato() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();

		return extractService.getExtrato(conta.getId());

	}

	public void depositaConta() {

		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");

		Conta conta = usuario.getConta();
		FacesContext context = FacesContext.getCurrentInstance();

		if (vlrDeposito != 0) {
			context.addMessage(null, new FacesMessage("Deposito realizado com sucesso. "));
		} else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Desculpe,falha ao depositar, valor invalido", null));
		}
		conta.setSaldo(conta.getSaldo() + this.vlrDeposito);

		conta.setUsuario(usuario);

		usuario.setConta(contaService.deposita(conta));

		gravaExtrato(conta.getId(), this.vlrDeposito, 'C');

	}

	public boolean saqueConta() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();
		FacesContext context = FacesContext.getCurrentInstance();
		if (conta == null || conta.getSaldo() < this.vlrSaque) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Impossivel realizar saque.", null));
			return false;
		}

		conta.setSaldo(conta.getSaldo() - this.vlrSaque);

		conta.setUsuario(usuario);

		usuario.setConta(contaService.saca(conta));

		gravaExtrato(conta.getId(), this.vlrSaque * (-1), 'D');

		return true;
	}

	public double mostraSaldo() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();

		return conta.getSaldo();

	}

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

		gravaExtrato(contaOrigem.getId(), this.vlrTransferencia * (-1), 'D');
		gravaExtrato(contaDestino.getId(), this.vlrTransferencia, 'C');

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

	private void gravaExtrato(long contaId, Double valor, char acao) {
		Extrato extrato = new Extrato();
		extrato.setAcao(acao);
		extrato.setData(new Date());
		extrato.setValor(valor);
		extrato.setContaId(contaId);

		extractService.gravarDados(extrato);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}

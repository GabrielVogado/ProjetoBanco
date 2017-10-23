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
	private Double rendimento = 0.005;
	private Double limite = 200.00;
	private Usuario usuario;

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
		gravaExtrato(conta.getId(), this.vlrDeposito, "Credito");

	}

	public boolean saqueConta() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();
		FacesContext context = FacesContext.getCurrentInstance();
		if (conta == null || conta.getSaldo() < this.vlrSaque) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Impossivel realizar saque.", null));
			return false;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Saque realizado com exito.", null));
			conta.setSaldo(conta.getSaldo() - this.vlrSaque);
			conta.setUsuario(usuario);
			usuario.setConta(contaService.saca(conta));
			gravaExtrato(conta.getId(), this.vlrSaque * (-1), "Debito");

		}
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
		Usuario usuarioDestino = usuarioService.getUsuarioById(Long.parseLong(idUsuarioDestino));
		if (usuarioDestino == null || usuarioDestino.getConta() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário/conta destino não encontrado!", null));
			return false;
		}

		if ((contaOrigem.getSaldo() < this.vlrTransferencia)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Saldo Insuficiente para Transação!", null));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Transação realizada com sucesso!", null));
		}

		if (contaOrigem.getSaldo() < this.vlrTransferencia)
			return false;
		System.out.println(usuarioDestino.getEmail());
		Conta contaDestino = usuarioDestino.getConta();
		contaDestino.setSaldo(contaDestino.getSaldo() + this.vlrTransferencia);
		contaDestino.setUsuario(usuarioDestino);
		contaOrigem.setSaldo(contaOrigem.getSaldo() - this.vlrTransferencia);
		contaOrigem.setUsuario(usuarioOrigem);
		usuarioOrigem.setConta(contaService.saca(contaOrigem));
		usuarioDestino.setConta(contaService.deposita(contaDestino));
		gravaExtrato(contaOrigem.getId(), this.vlrTransferencia * (-1), " Transferencia Debito");
		gravaExtrato(contaDestino.getId(), this.vlrTransferencia, "Transferencia Credito");

		return true;

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

	public void setIdUsuarioDestino(String usuarioId) {
		this.idUsuarioDestino = usuarioId;
	}

	public String getIdUsuarioDestino() {
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
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
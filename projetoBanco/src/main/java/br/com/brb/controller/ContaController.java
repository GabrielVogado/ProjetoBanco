package br.com.brb.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IContaService;

@ManagedBean(name = "contaController")
@SessionScoped
public class ContaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private IContaService contaService;
	private double vlrDeposito;
	private double vlrSaque;
	private double vlrTransferencia;

	public void depositaConta() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		Conta conta = usuario.getConta();

		if (conta == null) {
			conta = new Conta();
		}

		conta.setSaldo(conta.getSaldo() + this.vlrDeposito);

		conta.setUsuario(usuario);

		usuario.setConta(contaService.deposita(conta));

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

	public boolean transferenciaConta(Conta conta, Double valor) {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");
		conta = usuario.getConta();
			
			 if (conta.getSaldo()  < this.vlrSaque) {
			return false;
		} else {
			
			conta.setSaldo(conta.getSaldo() - this.vlrSaque);

			conta.setUsuario(usuario);

			usuario.setConta(contaService.saca(conta));
		}
			
			 conta.setSaldo(conta.getSaldo() + this.vlrDeposito);

				conta.setUsuario(usuario);

				usuario.setConta(contaService.deposita(conta));

			 
		this.vlrSaque = this.vlrDeposito + vlrSaque;			
			
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

}

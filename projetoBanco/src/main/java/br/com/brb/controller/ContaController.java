package br.com.brb.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IContaService;

@ManagedBean
@SessionScoped
public class ContaController {

	@EJB
	private IContaService contaService;
	private double vlrDeposito;
	private double vlrSaque;

	public void depositaConta() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		Conta conta = usuario.getConta();
		
		if( conta == null ) {
			conta = new Conta();
		}
		
		conta.setSaldo( conta.getSaldo() +  this.vlrDeposito);
		
		conta.setUsuario(usuario);
		
		usuario.setConta( contaService.deposita(conta) );

	}

	public boolean saqueConta() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		Conta conta = usuario.getConta();
		
		if( conta == null || conta.getSaldo() < this.vlrSaque ) {
			return false;
		}
		
		conta.setSaldo( conta.getSaldo() - this.vlrSaque);
		
		conta.setUsuario(usuario);
		
		usuario.setConta( contaService.deposita(conta) );
		
		return true;
	}

	public double getVlrDeposito() {
		return vlrDeposito;
	}

	public void setVlrDeposito(double vlrDeposito) {
		this.vlrDeposito = vlrDeposito;
	}


}

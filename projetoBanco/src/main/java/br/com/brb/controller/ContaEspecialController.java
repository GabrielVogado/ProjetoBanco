package br.com.brb.controller;

import javax.faces.context.FacesContext;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IContaService;

public class ContaEspecialController extends Conta {

	private static final long serialVersionUID = 1L;
	
	private Double limite = 200.00;
	
	private IContaService contaService;
	
	public void limiteEspecial() {
		
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");

		Conta conta = usuario.getConta();
		
		conta.setSaldo(conta.getSaldo() + limite);
		
		usuario.setConta(contaService.deposita(conta));
		
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}
	
	

}

package br.com.brb.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.brb.entity.Conta;
import br.com.brb.service.IContaService;

@ManagedBean
@SessionScoped
public class ContaController {

	@EJB
	private IContaService contaService;
	private Conta conta = new Conta();

	public void atividadeConta() {
		Conta adicionarSaldo = contaService.deposita(conta);

	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}

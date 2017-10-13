package br.com.brb.controller;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IContaPoupanca;
import br.com.brb.service.IContaService;

public class ContaPoupancaController extends Conta {

	private static final long serialVersionUID = 1L;

	@Inject
	private IContaService contaService;

	private Double rendimento = 0.500;
	
	@Inject
	private IContaPoupanca contaPoupanca;

	public void rendimentoPoupança() {
		

		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");

		Conta conta = usuario.getConta();
		
		conta.setSaldo(conta.getSaldo() * this.rendimento);
		
		usuario.setConta(contaPoupanca.taxaRendimento((ContaPoupancaController) conta));

	}

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}

}
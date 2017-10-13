package br.com.brb.service;

import br.com.brb.controller.ContaPoupancaController;
import br.com.brb.entity.Conta;

public interface IContaPoupanca {
	
	public Conta taxaRendimento(ContaPoupancaController poupanca);

}

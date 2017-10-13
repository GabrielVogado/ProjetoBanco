package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Conta;

@Local
public interface IContaPoupanca {
	
	public Conta taxaRendimento(Conta conta);

}

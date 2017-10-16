package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Conta;

@Local
public interface IContaEspecialService {
	
	public Conta limiteEspecial(Conta conta);

}

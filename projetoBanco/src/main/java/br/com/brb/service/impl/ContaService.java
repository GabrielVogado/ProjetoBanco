package br.com.brb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;
import br.com.brb.service.IContaService;

@Stateless
public class ContaService implements IContaService {
	
	@Inject
	ContaDao contadao;

	public Conta deposita(Conta valor) {
		return contadao.inserirSaldo(valor);
		
	}

	public void saca(Conta valor) {
		// TODO Auto-generated method stub
		
	}

	public void transfere(Conta valor) {
		// TODO Auto-generated method stub
		
	}

	public void consultaExtrato(Conta conta) {
		// TODO Auto-generated method stub
		
	}

	
}

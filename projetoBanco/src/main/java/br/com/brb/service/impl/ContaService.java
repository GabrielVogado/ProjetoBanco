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

	@Override
	public Conta deposita(Conta valor) {
		return contadao.inserirSaldo(valor);
		
	}

	@Override
	public void saca(Conta valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfere(Conta valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultaExtrato(Conta conta) {
		// TODO Auto-generated method stub
		
	}

	
}

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

	public Conta saca(Conta valor) {
		return contadao.inserirSaldo(valor);

	}

	public Conta transfere(Conta valor) {
		return contadao.inserirSaldo(valor);
	}

	public void consultaExtrato(Conta conta) {
		// TODO Auto-generated method stub

	}

}

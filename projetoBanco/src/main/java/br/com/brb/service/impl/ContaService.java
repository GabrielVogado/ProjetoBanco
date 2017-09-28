package br.com.brb.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;
import br.com.brb.service.IContaService;

@Stateless
public class ContaService implements IContaService {

	@EJB
	ContaDao contadao = new ContaDao();

	public Conta deposita(Conta valor) {
		return contadao.inserirSaldo(valor);

	}

	public Conta saca(Conta valor) {
		return contadao.inserirSaldo(valor);

	}

	public Conta transfere(Conta valor) {
		return contadao.inserirSaldo(valor);

	}

	@Override
	public List<Conta> consultaExtrato(Conta conta) {
		// TODO Auto-generated method stub
		return null;
	}

}

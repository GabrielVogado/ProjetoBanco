package br.com.brb.service.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.brb.dao.PoupancaDAO;
import br.com.brb.entity.Conta;
import br.com.brb.service.IContaPoupanca;

@Stateless
public class ContaPoupancaService implements IContaPoupanca {

	@EJB
	PoupancaDAO poupancaDao;

	@Override
	public Conta taxaRendimento(Conta conta) {
		return poupancaDao.rendimentoPoupanca(conta);

	}

}

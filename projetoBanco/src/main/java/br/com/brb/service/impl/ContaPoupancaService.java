package br.com.brb.service.impl;

import javax.ejb.EJB;
import javax.ejb.Local;

import br.com.brb.controller.ContaPoupancaController;
import br.com.brb.dao.PoupancaDAO;
import br.com.brb.entity.Conta;
import br.com.brb.service.IContaPoupanca;

@Local
public class ContaPoupancaService implements IContaPoupanca {
	
	@EJB
	PoupancaDAO poupancaDao;
	
	@Override
	public Conta taxaRendimento(ContaPoupancaController poupanca) {
		return poupancaDao.rendimentoPoupanca(poupanca);
		
		
	}

}

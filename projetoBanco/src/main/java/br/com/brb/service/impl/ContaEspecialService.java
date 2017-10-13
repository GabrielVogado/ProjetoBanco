package br.com.brb.service.impl;

import javax.ejb.EJB;

import br.com.brb.dao.EspecialDAO;
import br.com.brb.entity.Conta;
import br.com.brb.service.IContaEspecial;

public class ContaEspecialService implements IContaEspecial {

	@EJB
	EspecialDAO especialDao;

	@Override
	public Conta limiteEspecial(Conta conta) {
		return especialDao.limiteEspecial(conta);
	}

}

package br.com.brb.dao;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import br.com.brb.controller.ContaPoupancaController;
import br.com.brb.entity.Conta;

@Stateless
public class PoupancaDAO extends AbstractDAO {

	@Transactional
	public Conta rendimentoPoupanca(ContaPoupancaController poupanca) {

		try {
			poupanca = getEm().merge(poupanca);
			getEm().flush();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
}

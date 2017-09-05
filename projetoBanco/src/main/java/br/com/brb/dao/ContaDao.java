package br.com.brb.dao;

import javax.ejb.Stateless;

import br.com.brb.entity.Conta;

@Stateless
public class ContaDao extends AbstractDAO {
	
	public Conta inserirSaldo(Conta conta) {

		try {

			getEm().persist(conta);
			getEm().flush();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return conta;

	}

}

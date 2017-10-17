package br.com.brb.dao;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import br.com.brb.entity.Conta;

@Stateless
public class ContaDao extends AbstractDAO {
	@Transactional
	public Conta inserirSaldo(Conta conta) {

		try {
			if (conta.getId() == null) {
				getEm().persist(conta);
			}else {
				conta = getEm().merge(conta);
			}
			getEm().flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conta;

	}
	
	@Transactional
	public Conta gravar(Conta conta) {
		try {
			conta = getEm().merge(conta);
			getEm().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conta;
	}
	public Conta gravaNConta() {
		
		
		return null;
		
	}
}

package br.com.brb.dao;

import java.util.List;

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
			} else {
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

	@SuppressWarnings("unchecked")
	public List<Conta> finAll() {
		List<Conta> contas = getEm().createQuery("from Conta").getResultList();

		return contas;
	}

}

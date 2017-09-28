package br.com.brb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.brb.entity.Conta;

@Stateless
public class ContaDao extends AbstractDAO {
	@Transactional
	public Conta inserirSaldo(Conta conta) {

		try {

			conta = getEm().merge(conta);
			getEm().flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conta;
	}

	

	@SuppressWarnings("unchecked")
	public List<Conta> getList() {
		Query query = getEm().createQuery("Select From c Conta as c");
		List<Conta>contas = query.getResultList();
		
		return contas;
	}

}

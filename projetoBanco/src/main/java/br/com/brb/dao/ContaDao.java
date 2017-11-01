package br.com.brb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.brb.entity.Conta;

@Stateless
public class ContaDao extends AbstractDAO {

	@Transactional
	public Conta alterarSaldoConta(Conta conta) {
		try {
		conta = getEm().merge(conta); //para nessa linha
		getEm().flush(); //não faz o flush
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conta;
	}

//	@Transactional
//	public Conta inserirSaldo(Conta conta) {
//
//		try {
//			if (conta.getId() == null) {
//				getEm().persist(conta);
//			} else {
//				conta = getEm().merge(conta);
//			}
//			getEm().flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return conta;
//
//	}

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

	public Conta consultarContaPorId(Conta conta) throws NoResultException {

		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c FROM Conta c WHERE c.num_conta =:idconta");

		Query query = getEm().createQuery(hql.toString());

		query.setParameter("idconta", conta.getId());

		return (Conta) query.getSingleResult();
	}
}

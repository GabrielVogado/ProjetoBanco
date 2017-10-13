package br.com.brb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.brb.entity.Extrato;

@Stateless
public class ExtratoDAO extends AbstractDAO {

	public boolean gravarDados(Extrato extrato) {
		try {

			extrato = getEm().merge(extrato);
			getEm().flush();

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;

	}

	public List<Extrato> getExtrato(long contaId) {

		String hql = "SELECT e FROM Extrato e WHERE e.contaId=:contaId order by e.data";

		Query query = getEm().createQuery(hql);
		query.setParameter("contaId", contaId);

		return query.getResultList();
	}

}
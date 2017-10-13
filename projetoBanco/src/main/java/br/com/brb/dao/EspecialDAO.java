package br.com.brb.dao;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import br.com.brb.entity.Conta;

@Stateless
public class EspecialDAO extends AbstractDAO {

	@Transactional
	public Conta limiteEspecial(Conta conta) {
		try {
			conta = getEm().merge(conta);
			getEm().flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conta;

	}
}

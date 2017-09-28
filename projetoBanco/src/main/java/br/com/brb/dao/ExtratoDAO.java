package br.com.brb.dao;

import br.com.brb.entity.Extrato;

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

}

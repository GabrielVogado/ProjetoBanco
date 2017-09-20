package br.com.brb.dao;

import javax.ejb.Stateless;

import br.com.brb.entity.Cadastro;

@Stateless
public class CadastroDao extends AbstractDAO {
	
	public boolean insertCadastro(Cadastro cadastro) {

		try {
			getEm().persist(cadastro);
			getEm().flush();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
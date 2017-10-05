package br.com.brb.dao;

import javax.ejb.Stateless;

import br.com.brb.entity.Cadastro;

@Stateless
public class CadastroDao extends AbstractDAO {
	
	public Cadastro insertCadastro(Cadastro cadastro) {

		try {
			cadastro = getEm().merge(cadastro);
			getEm().flush();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return cadastro;
	}

}
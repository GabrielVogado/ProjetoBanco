package br.com.brb.dao;

import javax.ejb.Stateless;

import br.com.brb.entity.Cadastro;
import br.com.brb.entity.Conta;

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
	// public void merge(Cadastro cadastro) {
	// try {
	// getEm().getTransaction().begin();
	// getEm().merge(cadastro);
	// getEm().getTransaction().commit();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// }
	// }

	public void remove(Long Id) {
		try {
			Cadastro cadastro = getEm().find(Cadastro.class, Id);
			getEm().remove(cadastro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package br.com.brb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.CadastroDao;
import br.com.brb.entity.Cadastro;
import br.com.brb.service.ICadastroService;

@Stateless
public class CadastroService implements ICadastroService {


	
	@Inject
	CadastroDao cdao;

	public boolean gravarUsuario(Cadastro cadastro) {
		return cdao.insertCadastro(cadastro);
	}
}
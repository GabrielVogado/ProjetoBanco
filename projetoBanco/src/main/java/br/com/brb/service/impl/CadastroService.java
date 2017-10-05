package br.com.brb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.CadastroDao;
import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Cadastro;
import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.ICadastroService;

@Stateless
public class CadastroService implements ICadastroService {

	@Inject
	CadastroDao cdao;
	
	@Inject
	ContaDao contaDao;

	public boolean gravarUsuario(Cadastro cadastro) {
		cadastro = cdao.insertCadastro(cadastro);
		if (cadastro ==  null) {
			return false;
		}
		Usuario usuario = new Usuario();
		usuario.setId(cadastro.getId());
		
		Conta conta = new Conta();
		conta.setUsuario(usuario);
		
		contaDao.gravar(conta);
		
		return true;
	}
}
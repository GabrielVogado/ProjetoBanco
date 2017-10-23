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

	private Double limite = 200.00;

	public boolean gravarUsuario(Cadastro cadastro, String tipoConta) {

		Usuario usuario = new Usuario();

		Conta conta = new Conta();

		if (tipoConta.equals("ESPECIAL")) {

			conta = usuario.getConta();
			conta.setSaldo(conta.getSaldo() + limite);

		}

		cadastro = cdao.insertCadastro(cadastro);
		if (cadastro == null) {
			return false;
		}
		usuario.setId(cadastro.getId());
		conta.setUsuario(usuario);
		conta.setTipoConta(tipoConta);

		contaDao.gravar(conta);

		return true;
	}

	@Override
	public void remove(Long Id) {
		cdao.remove(Id);

	}
}
package br.com.brb.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;
import br.com.brb.service.IContaService;
import br.com.brb.service.IUsuarioService;

@Stateless
public class ContaService implements IContaService {

	@EJB
	ContaDao contadao = new ContaDao();
	@EJB
	IUsuarioService usuarioService;

	public Conta deposita(Conta valor) {
		/*Usuario user = usuarioService.getUsuarioById(valor.getUsuario().getId());
		valor.setUsuario(user);*/
		return contadao.inserirSaldo(valor);

	}

	public Conta saca(Conta valor) {

		/*Usuario user = usuarioService.getUsuarioById(valor.getUsuario().getId());
		valor.setUsuario(user);*/
		return contadao.inserirSaldo(valor);

	}

	public Conta transfere(Conta valor) {

		return contadao.inserirSaldo(valor);

	}

}

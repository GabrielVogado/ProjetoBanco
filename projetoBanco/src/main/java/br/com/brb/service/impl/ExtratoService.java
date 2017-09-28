package br.com.brb.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.brb.dao.ExtratoDAO;
import br.com.brb.service.IExtrato;

@Stateless
public class ExtratoService implements IExtrato {
	@EJB
	ExtratoDAO extractd = new ExtratoDAO();

	@Override
	public boolean gravarDados() {
		// TODO Auto-generated method stub
		return false;
	}

}

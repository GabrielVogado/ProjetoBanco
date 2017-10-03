package br.com.brb.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.brb.dao.ExtratoDAO;
import br.com.brb.entity.Extrato;
import br.com.brb.service.IExtratoService;

@Stateless
public class ExtratoService implements IExtratoService {
	@EJB
	ExtratoDAO extractd = new ExtratoDAO();

	@Override
	public boolean gravarDados(Extrato extrato) {
		extractd.gravarDados(extrato);
		return false;
	}

	@Override
	public List<Extrato> getExtrato(long id) {

		return extractd.getExtrato(id);

	}

}

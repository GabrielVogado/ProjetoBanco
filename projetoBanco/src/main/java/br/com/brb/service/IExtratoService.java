package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Extrato;

@Local
public interface IExtratoService {
	
	public boolean gravarDados(Extrato extrato);

}

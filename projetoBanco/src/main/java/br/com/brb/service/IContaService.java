package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Conta;

@Local
public interface IContaService {

	public Conta deposita(Conta valor);

	public void saca(Conta valor);

	public void transfere(Conta valor);

	public void consultaExtrato(Conta conta);

}

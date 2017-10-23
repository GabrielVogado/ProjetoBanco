package br.com.brb.controller;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;

@Stateless(name = "SchedulerController")
public class SchedulerController {

	@Inject
	ContaDao contaDao;

	private Double RENDIMENTO = 0.005;
	private Double TAXA_MANUTENCAO = 34.0;
	
	@Schedule(hour = "*", minute = "*/1", persistent = false)
	public void tempoRendimento() {

		List<Conta> contas = contaDao.finAll();
		
		for (Conta conta : contas) {
			if( conta.getTipoConta().equalsIgnoreCase( Conta.POUPANCA ) ) {
				conta.setSaldo( conta.getSaldo() + (conta.getSaldo() * RENDIMENTO) );
				contaDao.gravar(conta);				
			}
			if( conta.getTipoConta().equalsIgnoreCase( Conta.CORRENTE ) ) {
				conta.setSaldo( conta.getSaldo() - TAXA_MANUTENCAO );
				contaDao.gravar(conta);				
			}
		}

	}

}

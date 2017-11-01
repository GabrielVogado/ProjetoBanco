package br.com.brb.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;
import br.com.brb.entity.Extrato;
import br.com.brb.service.IExtratoService;

@Stateless(name = "SchedulerController")
public class SchedulerController {

	@Inject
	ContaDao contaDao;
	@Inject
	private IExtratoService extractService;

	private Double RENDIMENTO = 0.005;
	private Double TAXA_MANUTENCAO = 34.0;

	@Schedule(hour = "*", minute = "*/10", persistent = false)
	public void tempoRendimento() {
		try {
	
			List<Conta> contas = contaDao.finAll();
	
			for (Conta conta : contas) {
				if (conta.getTipoConta().equalsIgnoreCase(Conta.POUPANCA)) {
					conta.setSaldo(conta.getSaldo() + (conta.getSaldo() * RENDIMENTO));
					contaDao.gravar(conta);
					gravaExtrato(conta.getId(), conta.getSaldo(), "RENDIMENTO");
	
				}
				if (conta.getTipoConta().equalsIgnoreCase(Conta.CORRENTE)) {
					conta.setSaldo(conta.getSaldo() - TAXA_MANUTENCAO);
					contaDao.gravar(conta);
					gravaExtrato(conta.getId(), TAXA_MANUTENCAO * (-1), "TAXA_MANUTENCAO");
	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void gravaExtrato(long contaId, Double valor, String acao) {
		Extrato extrato = new Extrato();
		extrato.setAcao(acao);
		extrato.setData(new Date());
		extrato.setValor(valor);
		extrato.setContaId(contaId);

		extractService.gravarDados(extrato);
	}
}

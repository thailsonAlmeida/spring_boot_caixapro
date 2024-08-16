package com.caixapro.chain_responsability;

import com.caixapro.models.Conta;
import com.caixapro.repositories.ContaRepository;

public class SaldoValidator implements Validator {
	private ContaRepository contaRepository;
	private Validator next;	

	@Override
	public void validarDado(Long contaOrigemId, Long contaDestinoId, double valor) {
		
		Conta contaOrigem = contaRepository.findById(contaOrigemId).orElseThrow();
		
		if (contaOrigem.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
		
		if(next != null) {
        	next.validarDado(contaOrigemId, contaDestinoId, valor);
        }
		
	}

	@Override
	public void setNext(Validator next) {
		this.next = next;
		
	}

}

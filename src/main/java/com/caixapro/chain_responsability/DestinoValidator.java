package com.caixapro.chain_responsability;

import com.caixapro.repositories.ContaRepository;
import com.caixapro.services.ContaService;

public class DestinoValidator implements Validator {
	private ContaRepository contaRepository;
	private ContaService contaService;
	private Validator next;	

	
	@Override
	public void validarDado(Long contaOrigemId, Long contaDestinoId, double valor) {
		
		contaRepository.findById(contaDestinoId).orElseThrow();	
		
				
		contaService.depositar(contaDestinoId, valor);		
		
		if(next != null) {
			next.validarDado(contaOrigemId, contaDestinoId, valor);
		}
		
	}

	@Override
	public void setNext(Validator next) {
		this.next = next;
		
	}

}

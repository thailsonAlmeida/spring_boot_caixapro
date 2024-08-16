package com.caixapro.chain_responsability;

public interface Validator {
	
	void validarDado(Long contaOrigemId, Long contaDestinoId, double valor);
	
	void setNext(Validator next);

}

package com.caixapro.models;

import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {

	@Override
	public void exibirTipoConta() {
		System.out.println("Conta Corrente");
		
	}
	
}

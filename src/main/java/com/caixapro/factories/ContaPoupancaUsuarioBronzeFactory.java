package com.caixapro.factories;

import com.caixapro.models.Conta;
import com.caixapro.models.ContaPoupanca;
import com.caixapro.models.Usuario;
import com.caixapro.models.UsuarioBronze;

public class ContaPoupancaUsuarioBronzeFactory implements UsuarioContaFactory {

	@Override
	public Conta criarConta() {
		return new ContaPoupanca();
	}

	@Override
	public Usuario criarUsuario() {
		return new UsuarioBronze();
	}

}

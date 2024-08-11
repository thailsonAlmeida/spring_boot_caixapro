package com.caixapro.factories;

import com.caixapro.models.Conta;
import com.caixapro.models.ContaCorrente;
import com.caixapro.models.Usuario;
import com.caixapro.models.UsuarioBronze;

public class ContaCorrenteUsuarioBronzeFactory implements UsuarioContaFactory {

	@Override
	public Conta criarConta() {
		return new ContaCorrente();
	}

	@Override
	public Usuario criarUsuario() {
		return new UsuarioBronze();
	}

}

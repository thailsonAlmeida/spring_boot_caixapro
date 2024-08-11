package com.caixapro.factories;

import com.caixapro.models.Conta;
import com.caixapro.models.ContaCorrente;
import com.caixapro.models.Usuario;
import com.caixapro.models.UsuarioOuro;

public class ContaCorrenteUsuarioOuroFactory implements UsuarioContaFactory {

	@Override
	public Conta criarConta() {
		return new ContaCorrente();
	}

	@Override
	public Usuario criarUsuario() {
		return new UsuarioOuro();
	}

}

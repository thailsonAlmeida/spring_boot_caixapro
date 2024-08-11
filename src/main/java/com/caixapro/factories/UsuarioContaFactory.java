package com.caixapro.factories;

import com.caixapro.models.Conta;
import com.caixapro.models.Usuario;

public interface UsuarioContaFactory {
	
	Conta criarConta();
    Usuario criarUsuario();

}

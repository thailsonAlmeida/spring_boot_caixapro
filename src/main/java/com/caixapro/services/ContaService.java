package com.caixapro.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caixapro.factories.UsuarioContaFactory;
import com.caixapro.models.Conta;
import com.caixapro.models.Transacao;
import com.caixapro.models.Usuario;
import com.caixapro.repositories.ContaRepository;
import com.caixapro.repositories.TransacaoRepository;

@Service
public class ContaService {
	
	@Autowired
    private ContaRepository contaRepository;
    
    @Autowired
    private TransacaoRepository transacaoRepository;
    
    public Conta criarContaComUsuario(UsuarioContaFactory factory) {
    	Conta conta = factory.criarConta();
        Usuario usuario = factory.criarUsuario();
        conta.setTitular(usuario.getClass().getSimpleName());
        return contaRepository.save(conta);
    }
    
    public Conta getContaById(Long id) {
        return contaRepository.findById(id).orElseThrow();
    }
    
    public List<Conta> getTodasContas() {
    	return contaRepository.findAll();
    }
        
    public Conta depositar(Long contaId, double valor) {
    	Conta conta = checarExistenciaConta(contaId);
    	
    	chacarValorNegativo(valor);      	
    	
        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setTipo("DEPÓSITO");
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        transacao.setConta(conta);
        transacaoRepository.save(transacao);

        return conta;
    }
    
    public Conta sacar(Long contaId, double valor) {
    	Conta conta = checarExistenciaConta(contaId);
    	
    	chacarValorNegativo(valor);
        checarSaldoConta(conta, valor);
        
        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setTipo("SAQUE");
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        transacao.setConta(conta);
        transacaoRepository.save(transacao);

        return conta;
    }
    
    public Conta transferir(Long contaOrigemId, Long contaDestinoId, double valor) {    	
    	
    	Conta contaOrigem = checarExistenciaConta(contaOrigemId); 
    	chacarValorNegativo(valor);
    	checarSaldoConta(contaOrigem, valor);  
    	checarExistenciaConta(contaDestinoId);
    	
    	contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);    	
        depositar(contaDestinoId, valor); 	
    	    	
        contaRepository.save(contaOrigem);       
      
        Transacao transacao = new Transacao();
        transacao.setTipo("TRANSFERÊNCIA");
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        transacao.setConta(contaOrigem);
        transacaoRepository.save(transacao);

        return contaOrigem;
    }
    
    public List<Transacao> emitirExtrato(Long contaId) {
        return transacaoRepository.findByContaId(contaId);
    }
    
    public void chacarValorNegativo(double valor) {
    	if(valor < 0 ) {
    		throw new IllegalArgumentException("Digite um valor válido");
    	}
    }
    
    public void checarSaldoConta(Conta conta, double valor) {
    	if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
    
    public Conta checarExistenciaConta(Long contaID) {
    	
    	try {
    		contaRepository.findById(contaID).orElseThrow();
		} catch (Exception e) {
			throw new IllegalArgumentException("Conta inexistente");
		}
    	
    	Conta conta = contaRepository.findById(contaID).orElseThrow();    	
    	return conta;
    	
    }

}

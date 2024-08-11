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
    	Conta conta = contaRepository.findById(contaId).orElseThrow();
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
    	Conta conta = contaRepository.findById(contaId).orElseThrow();
        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
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
    	Conta contaOrigem = contaRepository.findById(contaOrigemId).orElseThrow();
        //Conta contaDestino = contaRepository.findById(contaDestinoId).orElseThrow();

        if (contaOrigem.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        //contaDestino.setSaldo(contaDestino.getSaldo() + valor);
                
        depositar(contaDestinoId, valor);

        contaRepository.save(contaOrigem);
        //contaRepository.save(contaDestino);

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

}

package com.caixapro.components;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caixapro.models.Conta;
import com.caixapro.models.ContaCorrente;
import com.caixapro.models.ContaPoupanca;
import com.caixapro.models.Transacao;
import com.caixapro.repositories.ContaRepository;
import com.caixapro.repositories.TransacaoRepository;

@Configuration
public class LoadDatabase {
	
	@Bean
    CommandLineRunner initDatabase(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        return args -> {
            Conta conta1 = new ContaPoupanca();
            conta1.setTitular("Usuario Ouro");
            conta1.setSaldo(1000.0);
            contaRepository.save(conta1);

            Conta conta2 = new ContaCorrente();
            conta2.setTitular("Usuario Prata");
            conta2.setSaldo(2000.0);
            contaRepository.save(conta2);
            
            Conta conta3 = new ContaCorrente();
            conta2.setTitular("Usuario Ouro");
            conta2.setSaldo(4000.0);
            contaRepository.save(conta3);

            Transacao transacao1 = new Transacao();
            transacao1.setTipo("DEPÓSITO");
            transacao1.setValor(500.0);
            transacao1.setData(LocalDateTime.now());
            transacao1.setConta(conta1);
            transacaoRepository.save(transacao1);

            Transacao transacao2 = new Transacao();
            transacao2.setTipo("SAQUE");
            transacao2.setValor(300.0);
            transacao2.setData(LocalDateTime.now());
            transacao2.setConta(conta2);
            transacaoRepository.save(transacao2);
        };
    }

}

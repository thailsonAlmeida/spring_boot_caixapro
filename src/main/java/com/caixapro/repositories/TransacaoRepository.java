package com.caixapro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixapro.models.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	List<Transacao> findByContaId(Long contaId);
}

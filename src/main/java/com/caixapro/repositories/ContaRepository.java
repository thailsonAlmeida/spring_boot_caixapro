package com.caixapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixapro.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}

package com.caixapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caixapro.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}

package com.compasso.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.api.resources.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	Cidade findByNome(String nome);

	Cidade findByEstado(String estado);
}

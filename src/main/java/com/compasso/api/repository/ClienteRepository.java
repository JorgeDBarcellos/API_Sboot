package com.compasso.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.compasso.api.resources.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}

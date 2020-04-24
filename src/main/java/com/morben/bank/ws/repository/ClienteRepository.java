package com.morben.bank.ws.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.morben.bank.ws.io.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<ClienteEntity, Long> { 

	//ClienteEntity findByEmail(String email);
	ClienteEntity findByClienteId(String clienteId);
}


package com.morben.bank.ws.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morben.bank.ws.io.entity.ContaCorrenteEntity;


@Repository
public interface ContaCorrenteRepository extends CrudRepository<ContaCorrenteEntity, Long> {

	ContaCorrenteEntity findByClienteId(String clienteId);

	Page<ContaCorrenteEntity> findAll(Pageable pageableRequest);
	
	//List<ContaEntity> findAllByAccountDetails(ContaEntity entity);
	//ContaEntity findByAccountId(String id);
}
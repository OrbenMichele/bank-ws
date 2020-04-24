package com.morben.bank.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morben.bank.ws.io.entity.PessoaEntity;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaEntity, Long>{

	//PessoaEntity findById(Long id);
	PessoaEntity findByEmail(String email);

	PessoaEntity findByClienteId(String clienteId);

	//PessoaEntity findByCl(String clienteId);

}

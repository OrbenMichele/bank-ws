package com.morben.bank.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morben.bank.ws.io.entity.PessoaFisicaEntity;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisicaEntity, Long> {

	PessoaFisicaEntity findByCpf(String cpf);

	//PessoaFisicaEntity findByClienteId(String clienteId);
	
}

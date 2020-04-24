package com.morben.bank.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morben.bank.ws.io.entity.EnderecoEntity;
import com.morben.bank.ws.io.entity.PessoaEntity;

@Repository
public interface EnderecoRepository extends CrudRepository<EnderecoEntity, Long> {

	//List<EnderecoEntity> findAllByPessoaDetalhes(PessoaEntity pessoaEntity);
	EnderecoEntity findByEnderecoId(String id);
	//EnderecoEntity findByClienteId(String clienteId);

	Iterable<EnderecoEntity> findAllByPessoaDetalhes(PessoaEntity pessoaEntity);
}

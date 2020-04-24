package com.morben.bank.ws.service;

import java.util.List;

import com.morben.bank.ws.io.entity.ContaCorrenteEntity;
import com.morben.bank.ws.io.entity.PessoaEntity;
import com.morben.bank.ws.shared.PessoaTipo;
import com.morben.bank.ws.shared.dto.ContaCorrenteDTO;

public interface ContaService {

	ContaCorrenteDTO getContasByClienteId(String clienteId);

	List<ContaCorrenteDTO> getContas(int page, int limit);
}

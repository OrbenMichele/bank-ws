package com.morben.bank.ws.service;

import java.util.List;

import com.morben.bank.ws.shared.dto.EnderecoDTO;

public interface EnderecoService {
	EnderecoDTO getEndereco(String id);
	List<EnderecoDTO> getEnderecosByClienteId(String clienteId);
}

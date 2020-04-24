package com.morben.bank.ws.service;

import java.util.List;

import com.morben.bank.ws.shared.dto.ClienteDTO;
import com.morben.bank.ws.shared.dto.ContaCorrenteDTO;

public interface ClienteService {

	ClienteDTO getClienteByClienteId(String id);

	List<ClienteDTO> getClientes(int page, int limit);

	ClienteDTO createCliente(ClienteDTO clienteDTO);

}

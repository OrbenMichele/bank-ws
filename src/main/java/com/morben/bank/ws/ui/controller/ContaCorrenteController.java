package com.morben.bank.ws.ui.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morben.bank.ws.service.ClienteService;
import com.morben.bank.ws.service.ContaService;
import com.morben.bank.ws.service.EnderecoService;
import com.morben.bank.ws.shared.dto.ContaCorrenteDTO;
import com.morben.bank.ws.ui.model.response.ContaCorrenteRest;

@RestController
@RequestMapping("/contas") //http://localhost:8080/clientes
public class ContaCorrenteController  implements Serializable{

	private static final long serialVersionUID = 1407327115468212868L;

	
	@Autowired 
	ClienteService clienteService;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired 
	ContaService contaService;


	@GetMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ContaCorrenteRest> getContas(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="25") int limit) {
	
		
		List<ContaCorrenteRest> returnValue = new ArrayList<>();

		List<ContaCorrenteDTO> contas = contaService.getContas(page, limit);
		
		Type listType = new TypeToken<List<ContaCorrenteRest>>() {}.getType();
		returnValue = new ModelMapper().map(contas, listType);
	
		return returnValue;
	}
	
	
	@GetMapping(
			path = "/{clienteId}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
	public ContaCorrenteRest getContasPorCliente(@PathVariable String clienteId) {
		
		ContaCorrenteRest returnValue = new ContaCorrenteRest();

		ContaCorrenteDTO contaDto = contaService.getContasByClienteId(clienteId);
		
		//Link addressLink = linkTo(UserController.class).slash(userId).slash("addresses").slash(addressId).withSelfRel();
		Link contaLink = linkTo(methodOn(ClienteController.class).getCliente(clienteId)).withSelfRel();
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(contaDto, ContaCorrenteRest.class);
		returnValue.add(contaLink);
		
		return returnValue;
	
	}
}

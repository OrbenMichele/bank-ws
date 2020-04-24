package com.morben.bank.ws.ui.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.morben.bank.ws.ui.model.request.EnderecoRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.service.ClienteService;
import com.morben.bank.ws.service.ContaService;
import com.morben.bank.ws.service.EnderecoService;
import com.morben.bank.ws.shared.dto.ClienteDTO;
import com.morben.bank.ws.shared.dto.PessoaDTO;
import com.morben.bank.ws.shared.dto.PessoaFisicaDTO;
import com.morben.bank.ws.shared.dto.PessoaJuridicaDTO;
import com.morben.bank.ws.ui.model.request.ClientePessoaFisicaRequestModel;
import com.morben.bank.ws.ui.model.request.ClientePessoaJuridicaRequestModel;
import com.morben.bank.ws.ui.model.response.*;

@RestController
@RequestMapping("/clientes") //http://localhost:8080/clientes
public class ClienteController {


	@Autowired 
	ClienteService clienteService;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired 
	ContaService contaService;

	@GetMapping(
			path = "/{clienteId}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
	public ClienteRest getCliente(@PathVariable String clienteId) {

		if (clienteId.length() < 30)
			throw new BankServiceException(ErrorMessages.LENGTH_FIELD.getErrorMessage());

		ClienteDTO clienteDto = clienteService.getClienteByClienteId(clienteId);

		if (clienteDto == null)
			throw new BankServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		Link contaLink = linkTo(methodOn(ClienteController.class).getCliente(clienteId)).withSelfRel();
		
		ModelMapper modelMapper = new ModelMapper();
		ClienteRest returnValue = modelMapper.map(clienteDto, ClienteRest.class);
		returnValue.add(contaLink);
		
		return returnValue;
	
	}

	
	@GetMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ClienteRest> getClientes(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="25") int limit) {

		List<ClienteDTO> clientes = clienteService.getClientes(page, limit);
		
		Type listType = new TypeToken<List<ClienteRest>>() {}.getType();

		return new ModelMapper().map(clientes, listType);
	}
	
	
	@PostMapping(
			path = "/PF",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ClienteRest createClientePessoaFisica(@RequestBody ClientePessoaFisicaRequestModel clienteDetails) throws Exception {
		
		if (clienteDetails.getCadastroDetalhes() == null)
			 throw new BankServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		String camposObrigatoriosMissing = this.validarCamposObrigatoriosClientePF(clienteDetails);
		if ( camposObrigatoriosMissing != null)
			throw new BankServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage() + camposObrigatoriosMissing);

		ClienteRest returnValue = new ClienteRest();
		ModelMapper modelMapper = new ModelMapper();
		
		PessoaDTO detalhesCadastro = modelMapper.map(clienteDetails.getCadastroDetalhes(), PessoaFisicaDTO.class);
	
		ClienteDTO clienteDTO = modelMapper.map(clienteDetails, ClienteDTO.class);
		clienteDTO.setCadastroDetalhes(detalhesCadastro);
		
		ClienteDTO createdCliente = clienteService.createCliente(clienteDTO);
		createdCliente.setCadastroDetalhes(modelMapper.map(createdCliente.getCadastroDetalhes(), PessoaFisicaDTO.class));

		returnValue = modelMapper.map(createdCliente, ClienteRest.class);
		returnValue.setCadastroDetalhes(modelMapper.map(createdCliente.getCadastroDetalhes(), PessoaFisicaRest.class));

		java.lang.reflect.Type listType = new TypeToken<List<EnderecoRest>>() {}.getType();
		returnValue.getCadastroDetalhes().setEnderecos(modelMapper.map(createdCliente.getCadastroDetalhes().getEnderecos(),listType));

		return returnValue;
	}
	
	@PostMapping(
			path = "/PJ",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ClienteRest createClientePessoaJuridica(@RequestBody ClientePessoaJuridicaRequestModel clienteDetails) throws Exception {
		
		if (clienteDetails.getCadastroDetalhes() == null)
			throw new BankServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		String camposObrigatoriosMissing = this.validarCamposObrigatoriosClientePJ(clienteDetails);

		if ( camposObrigatoriosMissing != null)
			throw new BankServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage() + camposObrigatoriosMissing);

		ClienteRest returnValue = new ClienteRest();
		ModelMapper modelMapper = new ModelMapper();
		
		PessoaDTO detalhesCadastro = modelMapper.map(clienteDetails.getCadastroDetalhes(), PessoaJuridicaDTO.class);
	
		ClienteDTO clienteDTO = modelMapper.map(clienteDetails, ClienteDTO.class);
		clienteDTO.setCadastroDetalhes(detalhesCadastro);
		
		ClienteDTO createdCliente = clienteService.createCliente(clienteDTO);
		createdCliente.setCadastroDetalhes(modelMapper.map(createdCliente.getCadastroDetalhes(), PessoaJuridicaDTO.class));

		returnValue = modelMapper.map(createdCliente, ClienteRest.class);
		returnValue.setCadastroDetalhes(modelMapper.map(createdCliente.getCadastroDetalhes(), PessoaJuridicaRest.class));

		java.lang.reflect.Type listType = new TypeToken<List<EnderecoRest>>() {}.getType();
		returnValue.getCadastroDetalhes().setEnderecos(modelMapper.map(createdCliente.getCadastroDetalhes().getEnderecos(),listType));

		return returnValue;
	}

	private String validarCamposObrigatoriosClientePF(ClientePessoaFisicaRequestModel clienteDetails){

		StringBuilder retorno = new StringBuilder();

		if (clienteDetails.getCadastroDetalhes() == null){
			retorno.append("cadastroDetalhes; ");
		} else {

			if (clienteDetails.getCadastroDetalhes().getCpf() == null  || clienteDetails.getCadastroDetalhes().getCpf().isEmpty()) {
				retorno.append("cpf; ");
			}else {
				clienteDetails.getCadastroDetalhes().setCpf(clienteDetails.getCadastroDetalhes().getCpf().replaceAll("[^0-9]*", ""));
				if (clienteDetails.getCadastroDetalhes().getCpf().length() != 11) {
					retorno.append("cpf; ");
				}
			}
			if (clienteDetails.getCadastroDetalhes().getNome() == null  || clienteDetails.getCadastroDetalhes().getNome().isEmpty()) {
				retorno.append("nome; ");
			}
			if (clienteDetails.getCadastroDetalhes().getRg() == null  || clienteDetails.getCadastroDetalhes().getRg().isEmpty()) {
				retorno.append("rg; ");
			}
			if (clienteDetails.getCadastroDetalhes().getOrgaoEmissor() == null  || clienteDetails.getCadastroDetalhes().getOrgaoEmissor().isEmpty()) {
				retorno.append("orgaoEmissor; ");
			}
			if (clienteDetails.getCadastroDetalhes().getEmail() == null  || clienteDetails.getCadastroDetalhes().getEmail().isEmpty()) {
				retorno.append("email; ");
			}
			if (clienteDetails.getCadastroDetalhes().getFone() == null  || clienteDetails.getCadastroDetalhes().getFone().isEmpty()) {
				retorno.append("fone; ");
			}else{
				clienteDetails.getCadastroDetalhes().setFone(clienteDetails.getCadastroDetalhes().getFone().replaceAll("[^0-9]*", ""));
				if (clienteDetails.getCadastroDetalhes().getFone().length() < 10)
					retorno.append("fone; ");
			}

			if (clienteDetails.getCadastroDetalhes().getEnderecos() == null) {
				retorno.append("enderecos; ");
			} else {

				this.validarCamposObrigatoriosEndereco(clienteDetails.getCadastroDetalhes().getEnderecos(), retorno);
			}

		}

		return retorno.length() > 0 ? " Dados incompletos, verifique o(s) campo(s): " + retorno : null;
	}

	private String validarCamposObrigatoriosClientePJ(ClientePessoaJuridicaRequestModel clienteDetails){

		StringBuilder retorno = new StringBuilder();

		if (clienteDetails.getCadastroDetalhes() == null){
			retorno.append("cadastroDetalhes; ");
		} else {

			if (clienteDetails.getCadastroDetalhes().getCnpj() == null || clienteDetails.getCadastroDetalhes().getCnpj().isEmpty()) {
				retorno.append("cnpj; ");
			}else {
				clienteDetails.getCadastroDetalhes().setCnpj(clienteDetails.getCadastroDetalhes().getCnpj().replaceAll("[^0-9]*", ""));
				if (clienteDetails.getCadastroDetalhes().getCnpj().length() != 14) {
					retorno.append("cnpj; ");
				}
			}
			if (clienteDetails.getCadastroDetalhes().getRazaoSocial() == null || clienteDetails.getCadastroDetalhes().getRazaoSocial().isEmpty()) {
				retorno.append("razaoSocial; ");
			}
			if (clienteDetails.getCadastroDetalhes().getInscricaoEstadual() == null || clienteDetails.getCadastroDetalhes().getInscricaoEstadual().isEmpty()) {
				retorno.append("inscricaoEstadual; ");
			}
			if (clienteDetails.getCadastroDetalhes().getFantasia() == null || clienteDetails.getCadastroDetalhes().getFantasia().isEmpty()) {
				retorno.append("fantasia; ");
			}
			if (clienteDetails.getCadastroDetalhes().getEmail() == null || clienteDetails.getCadastroDetalhes().getEmail().isEmpty()) {
				retorno.append("email; ");
			}
			if (clienteDetails.getCadastroDetalhes().getFone() == null  || clienteDetails.getCadastroDetalhes().getFone().isEmpty()) {
				retorno.append("fone; ");
			}else{
				clienteDetails.getCadastroDetalhes().setFone(clienteDetails.getCadastroDetalhes().getFone().replaceAll("[^0-9]*", ""));
				if (clienteDetails.getCadastroDetalhes().getFone().length() < 10)
					retorno.append("fone; ");
			}

			if (clienteDetails.getCadastroDetalhes().getEnderecos() == null) {
				retorno.append("enderecos; ");
			} else {

				this.validarCamposObrigatoriosEndereco(clienteDetails.getCadastroDetalhes().getEnderecos(), retorno);
			}

		}

		return retorno.length() > 0 ? "  Dados incompletos, verifique o(s) campo(s): " + retorno : null;
	}

	private void validarCamposObrigatoriosEndereco(List<EnderecoRequestModel> enderecos, StringBuilder retorno){

		for (EnderecoRequestModel endereco : enderecos) {

			if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
				retorno.append("bairro; ");
			}
			if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
				retorno.append("cep ;");
			}
			if (endereco.getEstado() == null || endereco.getEstado().isEmpty()) {
				retorno.append("estado; ");
			}
			if (endereco.getPais() == null || endereco.getPais().isEmpty()) {
				retorno.append("pais; ");
			}
			if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
				retorno.append("rua; ");
			}
		}
	}
}

package com.morben.bank.ws.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.morben.bank.ws.io.entity.*;
import com.morben.bank.ws.shared.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.repository.ClienteRepository;
import com.morben.bank.ws.repository.ContaCorrenteRepository;
import com.morben.bank.ws.repository.EnderecoRepository;
import com.morben.bank.ws.repository.PessoaRepository;
import com.morben.bank.ws.service.ClienteService;
import com.morben.bank.ws.shared.dto.CartaoDTO;
import com.morben.bank.ws.shared.dto.ClienteDTO;
import com.morben.bank.ws.shared.dto.ContaCorrenteDTO;
import com.morben.bank.ws.shared.dto.PessoaDTO;
import com.morben.bank.ws.shared.dto.PessoaFisicaDTO;
import com.morben.bank.ws.shared.dto.PessoaJuridicaDTO;
import com.morben.bank.ws.ui.model.response.ErrorMessages;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	Utils utils;


	@Override
	public ClienteDTO getClienteByClienteId(String clienteId) {
		
		ClienteEntity clienteEntity = this.clienteRepository.findByClienteId(clienteId);

		if (clienteEntity == null) throw new BankServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		
		ClienteDTO returnValue = modelMapper.map(clienteEntity, ClienteDTO.class);
		
//		PessoaEntity detalhesEntity;
//		if (returnValue.getPessoaTipo().name().equals(PessoaTipo.PF.name())){
//			detalhesEntity = (PessoaFisicaEntity) this.pessoaRepository.findByClienteId(clienteId);
//		} else {
//			detalhesEntity = (PessoaJuridicaEntity) this.pessoaRepository.findByClienteId(clienteId);
//		}
//
//		PessoaDTO detalhes = modelMapper.map(detalhesEntity,
//				returnValue.getPessoaTipo().name().equals(PessoaTipo.PF.name()) ?  PessoaFisicaDTO.class : PessoaJuridicaDTO.class);

//		returnValue.setCadastroDetalhes(detalhes);

		return returnValue;
	}

	
	@Override
	public List<ClienteDTO> getClientes(int page, int limit) {
		if (page > 0) page--; //in order to starts by page 1 instead of 0
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ClienteEntity> clientesPage = this.clienteRepository.findAll(pageableRequest);
		
		if (clientesPage == null) throw new BankServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		List<ClienteDTO> returnValue = new ArrayList();  
		List<ClienteEntity> clientes = clientesPage.getContent();

		for(ClienteEntity clienteEntity : clientes) {
			ClienteDTO clienteDto = new ClienteDTO();
			PessoaDTO pessoaDto = new PessoaDTO();
			BeanUtils.copyProperties(clienteEntity, clienteDto);
			PessoaEntity pessoa = this.pessoaRepository.findByClienteId(clienteDto.getClienteId());
			BeanUtils.copyProperties(pessoa, pessoaDto);
			clienteDto.setCadastroDetalhes(pessoaDto);
			returnValue.add(clienteDto);
		}
		
		return returnValue;
	}
	
	
	
	@Override
	public ClienteDTO createCliente(ClienteDTO cliente) {
		
		//if (pessoaRepository.findByEmail(cliente.getCadastroDetalhes().getEmail()) != null) throw new BankServiceException("Registro já existe!");


		ModelMapper modelMapper = new ModelMapper();
		PessoaEntity pessoaEntity = null;
		final String CLIENTE_ID = utils.generateClienteId(30);
		Random randomGenerator = new Random();

		Boolean pessoaFisica = (cliente.getPessoaTipo().name().equals(PessoaTipo.PF.name()));

		cliente.getCadastroDetalhes().setClienteId(CLIENTE_ID);
		cliente.getCadastroDetalhes().setScore(randomGenerator.nextInt(10)); // 0 - 9

		pessoaEntity = modelMapper.map(cliente.getCadastroDetalhes(), pessoaFisica ? PessoaFisicaEntity.class : PessoaJuridicaEntity.class);

		List<EnderecoEntity> enderecosEntity = pessoaEntity.getEnderecos();
		
 		for(int i=0;i<enderecosEntity.size();i++)
		{
			EnderecoEntity endereco =  enderecosEntity.get(i);
			endereco.setPessoaDetalhes(pessoaEntity);
			endereco.setEnderecoId(utils.generateEnderecoId(30));
			endereco.setClienteId(CLIENTE_ID);
			enderecosEntity.set(i, endereco);
		}
 		
 		pessoaEntity.setEnderecos(enderecosEntity);
		ClienteEntity clienteEntity = modelMapper.map(cliente, ClienteEntity.class);
		pessoaEntity.setClienteDetalhes(clienteEntity);
		
		clienteEntity.setCadastroDetalhes(pessoaEntity);
		
		clienteEntity.setClienteId(CLIENTE_ID);

		ContaCorrenteEntity contaCorrenteEntity = this.createContaCorrente(pessoaEntity, cliente.getPessoaTipo());
	    clienteEntity.setContaDetalhes(contaCorrenteEntity);
	    clienteEntity.getContaDetalhes().setClienteDetalhes(clienteEntity);
	    
		ClienteEntity storedClienteDetails = clienteRepository.save(clienteEntity);
		
		ClienteDTO returnValue =modelMapper.map(storedClienteDetails, ClienteDTO.class);
		returnValue.setCadastroDetalhes(modelMapper.map(storedClienteDetails.getCadastroDetalhes(),  
				pessoaFisica ? PessoaFisicaDTO.class : PessoaJuridicaDTO.class));

		//amazonSES.verifyEmail(returnValue);
		
		return returnValue;
		
	}

	private ContaCorrenteEntity createContaCorrente(PessoaEntity pessoa, PessoaTipo pessoaTipo) {

		ModelMapper modelMapper = new ModelMapper();

		ContaCorrenteEntity contaCorrente = new ContaCorrenteEntity();
		contaCorrente.setClienteId(pessoa.getClienteId());
		contaCorrente.setContaId(utils.generateContaId(Constantes.PUBLIC_ID_TAMANHO));
		contaCorrente.setAgencia(Constantes.AGENCIA);
		contaCorrente.setLimite(LimiteContaPorScore.getLimite(pessoa.getScore()));
		contaCorrente.setNumero(utils.generateNumeroConta(Constantes.NUMERO_CONTA_TAMANHO));
		contaCorrente.setSaldo(0.0);
		contaCorrente.setTipo(pessoaTipo.tipoConta());

		List<CartaoEntity> cartoes =  new ArrayList();

		if (LimiteCartaoPorScore.getLimite(pessoa.getScore()) > 0.0 ) {

			CartaoEntity cartao = new CartaoEntity();
			cartao.setCartaoId(utils.generateCartaoId(Constantes.PUBLIC_ID_TAMANHO));
			cartao.setCcv(utils.generateNumeroCCVCartao(Constantes.NUMERO_CCV_CARTAO_TAMANHO));
			cartao.setContaId(contaCorrente.getContaId());
			cartao.setClienteId(contaCorrente.getContaId());
			cartao.setLimite(LimiteCartaoPorScore.getLimite(pessoa.getScore()));
			cartao.setNumero(utils.generateNumeroCartao(Constantes.NUMERO_CARTAO_TAMANHO));
			cartao.setSaldo(LimiteCartaoPorScore.getLimite(pessoa.getScore()));
			cartao.setValidade(utils.getDataVencimento());
			cartao.setContaDetalhes(contaCorrente);

			cartoes.add(cartao);
			contaCorrente.setCartoesDetalhes(cartoes);
		}

		return contaCorrente;
	}
	
}


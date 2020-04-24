package com.morben.bank.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.morben.bank.ws.io.entity.PessoaEntity;
import com.morben.bank.ws.shared.Constantes;
import com.morben.bank.ws.shared.LimiteCartaoPorScore;
import com.morben.bank.ws.shared.PessoaTipo;
import com.morben.bank.ws.shared.dto.CartaoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.io.entity.ClienteEntity;
import com.morben.bank.ws.io.entity.ContaCorrenteEntity;
import com.morben.bank.ws.repository.ClienteRepository;
import com.morben.bank.ws.repository.ContaCorrenteRepository;

import com.morben.bank.ws.service.ContaService;
import com.morben.bank.ws.shared.Utils;
import com.morben.bank.ws.shared.dto.ClienteDTO;
import com.morben.bank.ws.shared.dto.ContaCorrenteDTO;
import com.morben.bank.ws.ui.model.response.ErrorMessages;

@Service
public class ContaServiceImpl implements ContaService{


	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public List<ContaCorrenteDTO> getContas(int page, int limit) {
		
		if (page > 0) page--; //in order to starts by page 1 instead of 0
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ContaCorrenteEntity> contasPage = this.contaCorrenteRepository.findAll(pageableRequest);
		
		if (contasPage == null) throw new BankServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		List<ContaCorrenteDTO> returnValue = new ArrayList();  
		List<ContaCorrenteEntity> contas = contasPage.getContent();

		for(ContaCorrenteEntity contasEntity : contas) {
			ContaCorrenteDTO contaDto = new ContaCorrenteDTO();
			ClienteDTO clienteDto = new ClienteDTO();
			BeanUtils.copyProperties(contasEntity, contaDto);
			ClienteEntity cliente = this.clienteRepository.findByClienteId(contaDto.getClienteId());
			BeanUtils.copyProperties(cliente, clienteDto);
			contaDto.setClienteDetalhes(clienteDto);
			returnValue.add(contaDto);
		}
		
		return returnValue;
	}
	
	@Override
	public ContaCorrenteDTO getContasByClienteId(String clienteId) {
		
		//ClienteEntity clienteEntity = this.contaCorrenteRepository.findByClienteId(clienteId);
		ContaCorrenteEntity detalhesEntity = this.contaCorrenteRepository.findByClienteId(clienteId);

		if (detalhesEntity == null) throw new BankServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		
		ContaCorrenteDTO returnValue = modelMapper.map(detalhesEntity, ContaCorrenteDTO.class);
		
		return returnValue;
	}



}

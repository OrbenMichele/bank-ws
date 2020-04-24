package com.morben.bank.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morben.bank.ws.io.entity.EnderecoEntity;
import com.morben.bank.ws.io.entity.PessoaEntity;
import com.morben.bank.ws.repository.EnderecoRepository;
import com.morben.bank.ws.repository.PessoaRepository;
import com.morben.bank.ws.service.EnderecoService;
import com.morben.bank.ws.shared.dto.EnderecoDTO;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired 
	PessoaRepository pessoaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Override
	public EnderecoDTO getEndereco(String enderecoId) {
		EnderecoDTO returnValue = null;

		EnderecoEntity enderecoEntity = enderecoRepository.findByEnderecoId(enderecoId);
	    
	    if(enderecoEntity!=null)
	    {
	        returnValue = new ModelMapper().map(enderecoEntity, EnderecoDTO.class);
	    }

	    return returnValue;

	}

	@Override
	public List<EnderecoDTO> getEnderecosByClienteId(String clienteId) {
		List<EnderecoDTO> returnValue = new ArrayList<>();
	    ModelMapper modelMapper = new ModelMapper();
	    
	    PessoaEntity pessoaEntity = pessoaRepository.findByClienteId(clienteId);
	    if(pessoaEntity==null) return returnValue;

	    Iterable<EnderecoEntity> addresses = enderecoRepository.findAllByPessoaDetalhes(pessoaEntity);
	    for(EnderecoEntity addressEntity:addresses)
	    {
	        returnValue.add( modelMapper.map(addressEntity, EnderecoDTO.class) );
	    }		return null;
	}

}





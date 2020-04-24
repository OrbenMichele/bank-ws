package com.morben.bank.ws.shared.dto;

import java.io.Serializable;

import com.morben.bank.ws.shared.PessoaTipo;

public class ClienteDTO implements Serializable{

	
	private static final long serialVersionUID = -1303225638326004290L;
	
	private Long id;
	private String clienteId;
	private PessoaTipo pessoaTipo;
	private PessoaDTO cadastroDetalhes;
	private ContaCorrenteDTO contaDetalhes;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public PessoaTipo getPessoaTipo() {
		return pessoaTipo;
	}
	public void setPessoaTipo(PessoaTipo pessoaTipo) {
		this.pessoaTipo = pessoaTipo;
	}
	public PessoaDTO getCadastroDetalhes() {
		return cadastroDetalhes;
	}
	public void setCadastroDetalhes(PessoaDTO cadastroDetalhes) {
		this.cadastroDetalhes = cadastroDetalhes;
	}
	public ContaCorrenteDTO getContaDetalhes() {
		return contaDetalhes;
	}
	public void setContaDetalhes(ContaCorrenteDTO contaDetalhes) {
		this.contaDetalhes = contaDetalhes;
	}


}
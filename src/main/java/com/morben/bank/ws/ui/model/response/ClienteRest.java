package com.morben.bank.ws.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

import com.morben.bank.ws.shared.PessoaTipo;

public class ClienteRest extends RepresentationModel<ClienteRest>{
	
	private PessoaTipo pessoaTipo;
	private String clienteId;
	private PessoaRest cadastroDetalhes;
	private ContaCorrenteRest contaDetalhes;


	public PessoaTipo getPessoaTipo() {
		return pessoaTipo;
	}
	public void setPessoaTipo(PessoaTipo pessoaTipo) {
		this.pessoaTipo = pessoaTipo;
	}
	
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public PessoaRest getCadastroDetalhes() {
		return cadastroDetalhes;
	}
	public void setCadastroDetalhes(PessoaRest cadastroDetalhes) {
		this.cadastroDetalhes = cadastroDetalhes;
	}
	public ContaCorrenteRest getContaDetalhes() {
		return contaDetalhes;
	}
	public void setContaDetalhes(ContaCorrenteRest contaDetalhes) {
		this.contaDetalhes = contaDetalhes;
	}

}

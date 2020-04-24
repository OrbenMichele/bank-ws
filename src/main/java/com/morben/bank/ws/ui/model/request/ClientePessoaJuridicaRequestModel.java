package com.morben.bank.ws.ui.model.request;


public class ClientePessoaJuridicaRequestModel extends ClienteRequestModel{
	
	private PessoaJuridicaRequestModel cadastroDetalhes;

	public PessoaJuridicaRequestModel getCadastroDetalhes() {
		return cadastroDetalhes;
	}

	public void setCadastroDetalhes(PessoaJuridicaRequestModel cadastroDetalhes) {
		this.cadastroDetalhes = cadastroDetalhes;
	}

}

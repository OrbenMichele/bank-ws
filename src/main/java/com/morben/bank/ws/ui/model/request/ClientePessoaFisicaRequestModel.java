package com.morben.bank.ws.ui.model.request;


public class ClientePessoaFisicaRequestModel extends ClienteRequestModel{
	
	private PessoaFisicaRequestModel cadastroDetalhes;

	public PessoaFisicaRequestModel getCadastroDetalhes() {
		return cadastroDetalhes;
	}

	public void setCadastroDetalhes(PessoaFisicaRequestModel cadastroDetalhes) {
		this.cadastroDetalhes = cadastroDetalhes;
	}

}

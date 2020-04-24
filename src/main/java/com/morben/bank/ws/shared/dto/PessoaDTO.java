package com.morben.bank.ws.shared.dto;

import java.util.List;


public class PessoaDTO {

	private Long id;
	private String clienteId;
    private String email;
    private String fone;
	private int score;

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private List<EnderecoDTO> enderecos;

	private ClienteDTO clienteDetalhes; 

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public ClienteDTO getClienteDetalhes() {
		return clienteDetalhes;
	}
	public void setClienteDetalhes(ClienteDTO clienteDetalhes) {
		this.clienteDetalhes = clienteDetalhes;
	}
	
   
}


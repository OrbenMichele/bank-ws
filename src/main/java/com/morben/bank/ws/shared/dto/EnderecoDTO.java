package com.morben.bank.ws.shared.dto;

import java.io.Serializable;

public class EnderecoDTO implements Serializable{
	

	private static final long serialVersionUID = -8392057734918734197L;
	
	private Long id;
	private String enderecoId;
	private String clienteId;
	private String rua;
	private String complemento;
	private int numero;
	private String estado;
	private String pais;
	private String bairro;
	private String cep;
	private PessoaDTO pessoaDetalhes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnderecoId() {
		return enderecoId;
	}
	public void setEnderecoId(String enderecoId) {
		this.enderecoId = enderecoId;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public PessoaDTO getPessoaDetalhes() {
		return pessoaDetalhes;
	}
	public void setPessoaDetalhes(PessoaDTO pessoaDetalhes) {
		this.pessoaDetalhes = pessoaDetalhes;
	}
	
	
}

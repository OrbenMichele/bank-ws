package com.morben.bank.ws.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="enderecos")
public class EnderecoEntity implements Serializable{

	private static final long serialVersionUID = 7947603627803133658L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 30)
	private String enderecoId;
	
	@Column(length=30, nullable=false)
	private String clienteId;
	
	@Column(nullable = false, length = 150)
	private String rua;
	
	@Column(length = 50)
	private String complemento;
	
	@Column(nullable = false, length = 10)
	private int numero;
	
	@Column(nullable = false, length = 30)
	private String estado;
	
	@Column(nullable = false, length = 30)
	private String pais;
	
	@Column(nullable = false, length = 100)
	private String bairro;
	
	@Column(nullable = false, length = 30)
	private String cep;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="pessoas_id")
	private PessoaEntity pessoaDetalhes;


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

	public PessoaEntity getPessoaDetalhes() {
		return pessoaDetalhes;
	}

	public void setPessoaDetalhes(PessoaEntity pessoaDetalhes) {
		this.pessoaDetalhes = pessoaDetalhes;
	}
}

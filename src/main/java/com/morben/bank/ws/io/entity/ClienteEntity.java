package com.morben.bank.ws.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.morben.bank.ws.shared.PessoaTipo;

@Entity(name="clientes")
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = 5399914182424085196L;
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(length=30, nullable=false)
	private String clienteId;

	@Column(length=2, nullable=false)
	private PessoaTipo pessoaTipo;
	
	@OneToOne(mappedBy="clienteDetalhes", cascade=CascadeType.PERSIST)
	private PessoaEntity cadastroDetalhes;

	@OneToOne(mappedBy="clienteDetalhes", cascade=CascadeType.PERSIST)
	private ContaCorrenteEntity contaDetalhes;

	
	public PessoaEntity getCadastroDetalhes() {
		return cadastroDetalhes;
	}

	public void setCadastroDetalhes(PessoaEntity cadastroDetalhes) {
		this.cadastroDetalhes = cadastroDetalhes;
	}

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

	public ContaCorrenteEntity getContaDetalhes() {
		return contaDetalhes;
	}

	public void setContaDetalhes(ContaCorrenteEntity contaDetalhes) {
		this.contaDetalhes = contaDetalhes;
	}



}




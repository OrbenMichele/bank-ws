package com.morben.bank.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="INS")
@Entity(name="pessoas")
public class PessoaEntity implements Serializable{

	private static final long serialVersionUID = -3797443748287933049L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=30, nullable=false)
	private String clienteId;

	@Column(length=1, nullable=false)
	private int score;

	//@Column(nullable = false, length = 200, unique=true)
	@Column(nullable = false, length = 200)
    private String email;
	
	@Column(nullable = false, length = 50)
    private String fone;
	
	@OneToMany(mappedBy="pessoaDetalhes", cascade = CascadeType.PERSIST)
	private List<EnderecoEntity> enderecos;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="pessoas_id")
	private ClienteEntity clienteDetalhes;

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

	public List<EnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

	public ClienteEntity getClienteDetalhes() {
		return clienteDetalhes;
	}

	public void setClienteDetalhes(ClienteEntity clienteDetalhes) {
		this.clienteDetalhes = clienteDetalhes;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

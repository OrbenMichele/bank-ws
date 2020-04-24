package com.morben.bank.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PJ")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class PessoaJuridicaEntity extends PessoaEntity implements Serializable{

	private static final long serialVersionUID = 1077614656614666706L;
	
	@Column(length=15)
	private String inscricaoEstadual;
	
	@Column(length=14)
    private String cnpj;
	
	@Column(length = 200)
    private String razaoSocial;
	
	@Column(length = 80 )
    private String fantasia;

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	
	
}

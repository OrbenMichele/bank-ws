package com.morben.bank.ws.shared.dto;

import java.io.Serializable;


public class PessoaJuridicaDTO extends PessoaDTO implements Serializable{
    
	
	private static final long serialVersionUID = -651531848389243398L;
	
	private String inscricaoEstadual;
	private String cnpj;
    private String razaoSocial; 
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
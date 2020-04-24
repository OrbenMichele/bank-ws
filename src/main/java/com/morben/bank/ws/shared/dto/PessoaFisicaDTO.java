package com.morben.bank.ws.shared.dto;

import java.io.Serializable;

public class PessoaFisicaDTO extends PessoaDTO implements Serializable{
	

	private static final long serialVersionUID = 5705621732796696311L;
	
    private String nome;
    private String apelido;
	private String rg;
    private String orgaoEmissor;
    private String cpf;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}
	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    

}


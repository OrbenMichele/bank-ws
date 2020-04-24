package com.morben.bank.ws.ui.model.response;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;


public class PessoaRest extends RepresentationModel<PessoaRest>{

    private String email;
    private String fone;
	private int score;
    private List<EnderecoRest> enderecos;


	public List<EnderecoRest> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoRest> enderecos) {
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}

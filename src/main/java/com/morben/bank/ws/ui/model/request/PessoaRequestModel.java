package com.morben.bank.ws.ui.model.request;

import java.time.LocalDate;
import java.util.List;


public class PessoaRequestModel {

    private List<EnderecoRequestModel> enderecos;
    private String email;
    private String fone;
    private int score;

	public List<EnderecoRequestModel> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoRequestModel> enderecos) {
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

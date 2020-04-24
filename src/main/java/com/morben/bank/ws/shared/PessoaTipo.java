package com.morben.bank.ws.shared;

public enum PessoaTipo {

	PF("PESSOA_FISICA"), PJ("PPESSOA_JURIDICA");
	
	private String value;
	
	private PessoaTipo(String value){
	    this.value = value;
	}
	public String getValue(){
	    return value;
	}
	public String tipoConta() {
		return this.equals(PessoaTipo.PF) ? "C" : "E";
	}
}

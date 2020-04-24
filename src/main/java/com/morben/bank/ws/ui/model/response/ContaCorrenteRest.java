package com.morben.bank.ws.ui.model.response;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class ContaCorrenteRest extends  RepresentationModel<ContaCorrenteRest>{

	private String contaId; 
	private String clienteId;
	private String tipo;
	private String agencia;
	private String numero;
	private Double saldo;
	private Double limite;
	private List<CartaoRest> cartoesDetalhes;
	
	public String getContaId() {
		return contaId;
	}
	public void setContaId(String contaId) {
		this.contaId = contaId;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getLimite() {
		return limite;
	}
	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public List<CartaoRest> getCartoesDetalhes() {
		return cartoesDetalhes;
	}
	public void setCartoesDetalhes(List<CartaoRest> cartoesDetalhes) {
		this.cartoesDetalhes = cartoesDetalhes;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}

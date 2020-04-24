package com.morben.bank.ws.ui.model.response;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.morben.bank.ws.io.entity.ContaCorrenteEntity;


public class CartaoRest extends  RepresentationModel<CartaoRest>{

	private String cartaoId;
	private String clienteId;
	private String contaId;
	private String numero;
	private Date validade;
	private String ccv;
	private Double limite;
	private Double saldo;
	//private ContaCorrenteRest contaDetalhes;
	
	public String getCartaoId() {
		return cartaoId;
	}
	public void setCartaoId(String cartaoId) {
		this.cartaoId = cartaoId;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public String getContaId() {
		return contaId;
	}
	public void setContaId(String contaId) {
		this.contaId = contaId;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getCcv() {
		return ccv;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	public Double getLimite() {
		return limite;
	}
	public void setLimite(Double limite) {
		this.limite = limite;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
//	public ContaCorrenteRest getContaDetalhes() {
//		return contaDetalhes;
//	}
//	public void setContaDetalhes(ContaCorrenteRest contaDetalhes) {
//		this.contaDetalhes = contaDetalhes;
//	}
	
	
}

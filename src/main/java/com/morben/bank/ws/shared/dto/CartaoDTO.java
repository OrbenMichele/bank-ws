package com.morben.bank.ws.shared.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.morben.bank.ws.io.entity.ContaCorrenteEntity;

public class CartaoDTO implements Serializable{

	private static final long serialVersionUID = 6379020007438968839L;
	
	private Long id;
	private String cartaoId;
	private String clienteId;
	private String contaId;
	private String numero;
	private Date validade;
	private String ccv;
	private Double limite;
	private Double saldo = 0.0;
	private ContaCorrenteDTO contaDetalhes;
	
	public CartaoDTO() {};
	
	public CartaoDTO(String cartaoId, String clienteId, String contaId, String numero, Date validade, String ccv,
			Double limite) {
		super();
		this.cartaoId = cartaoId;
		this.clienteId = clienteId;
		this.contaId = contaId;
		this.numero = numero;
		this.validade = validade;
		this.ccv = ccv;
		this.limite = limite;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public ContaCorrenteDTO getContaDetalhes() {
		return contaDetalhes;
	}
	public void setContaDetalhes(ContaCorrenteDTO contaDetalhes) {
		this.contaDetalhes = contaDetalhes;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}

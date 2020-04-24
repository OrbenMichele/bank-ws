package com.morben.bank.ws.io.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.morben.bank.ws.shared.Constantes;
import com.morben.bank.ws.shared.dto.ContaCorrenteDTO;

@Entity(name="cartoes")
public class CartaoEntity implements Serializable{

	private static final long serialVersionUID = 841036170703835690L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String cartaoId;
	
	@Column(nullable = false, length = 30)
	private String clienteId;
	
	@Column(nullable = false, length = 30)
	private String contaId;
	
	@Column(nullable = false, length = Constantes.NUMERO_CARTAO_TAMANHO)
	private String numero;

	@Column(nullable = false)
	private Date validade;
	
	@Column(nullable = false, length = Constantes.NUMERO_CCV_CARTAO_TAMANHO)
	private String ccv;
	
	@Column(nullable = false)
	private Double limite;
	
	@Column(nullable = false)
	private Double saldo;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="contas_id")
	private ContaCorrenteEntity contaDetalhes;
	
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public ContaCorrenteEntity getContaDetalhes() {
		return contaDetalhes;
	}

	public void setContaDetalhes(ContaCorrenteEntity contaDetalhes) {
		this.contaDetalhes = contaDetalhes;
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

}

package com.morben.bank.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.morben.bank.ws.shared.Constantes;

@Entity(name="contas")
public class ContaCorrenteEntity implements Serializable {
	
	private static final long serialVersionUID = 7387445824300948118L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=30, nullable=false)
	private String contaId; //public Id
	
	@Column(length=30, nullable=false)
	private String clienteId;
	
	@Column(length=4, nullable=false)
	private String agencia;
	
	@Column(length=Constantes.NUMERO_CONTA_TAMANHO, nullable=false)
	private String numero;
	
	@Column(length=1, nullable=false)
	private String tipo;
	
	@Column(nullable=false)
	private Double saldo;
	
	@Column(nullable=false)
	private Double limite;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="clientes_id")
	private ClienteEntity clienteDetalhes;
	
	@OneToMany(mappedBy="contaDetalhes", cascade=CascadeType.PERSIST)
	private List<CartaoEntity> cartoesDetalhes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public ClienteEntity getClienteDetalhes() {
		return clienteDetalhes;
	}

	public void setClienteDetalhes(ClienteEntity clienteDetalhes) {
		this.clienteDetalhes = clienteDetalhes;
	}

	public List<CartaoEntity> getCartoesDetalhes() {
		return cartoesDetalhes;
	}

	public void setCartoesDetalhes(List<CartaoEntity> cartoesDetalhes) {
		this.cartoesDetalhes = cartoesDetalhes;
	}
}

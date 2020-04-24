package com.morben.bank.ws.shared.dto;

import java.io.Serializable;
import java.util.List;


import com.morben.bank.ws.shared.LimiteContaPorScore;

public class ContaCorrenteDTO implements Serializable {
	
	private static final long serialVersionUID = -6081593863844344656L;

	private Long id;
	private String contaId; //public Id
	private String clienteId;
	private String agencia;
	private String numero;
	private String tipo;
	private Double saldo = 0.0;
	private Double limite = 0.0;
	private ClienteDTO clienteDetalhes;
	private List<CartaoDTO> cartoesDetalhes;

	public ContaCorrenteDTO() {}
	
	public ContaCorrenteDTO(String contaId, String clienteId, String agencia, String numero, String tipo, int score) {
		super();
		this.contaId = contaId;
		this.clienteId = clienteId;
		this.agencia = agencia;
		this.numero = numero;
		this.tipo = tipo;
		this.limite = LimiteContaPorScore.getLimite(score);
	}

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

	public ClienteDTO getClienteDetalhes() {
		return clienteDetalhes;
	}

	public void setClienteDetalhes(ClienteDTO clienteDetalhes) {
		this.clienteDetalhes = clienteDetalhes;
	}

	public List<CartaoDTO> getCartoesDetalhes() {
		return cartoesDetalhes;
	}

	public void setCartoesDetalhes(List<CartaoDTO> cartoesDetalhes) {
		this.cartoesDetalhes = cartoesDetalhes;
	}
}


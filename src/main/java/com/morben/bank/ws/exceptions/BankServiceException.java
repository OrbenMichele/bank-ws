package com.morben.bank.ws.exceptions;

public class BankServiceException extends RuntimeException{

	private static final long serialVersionUID = 354325767610593611L;

	public BankServiceException(String message){
		super(message);
	}
}
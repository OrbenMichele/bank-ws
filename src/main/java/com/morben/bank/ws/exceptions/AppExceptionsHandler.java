package com.morben.bank.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.morben.bank.ws.ui.model.response.ErrorMessage;



@ControllerAdvice
public class AppExceptionsHandler {
	
	@ExceptionHandler(value = BankServiceException.class)
	public ResponseEntity<Object> handlerUserServiceException(BankServiceException ex, 
			WebRequest req){
		
		ErrorMessage erroMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(erroMessage, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handlerOthersException(Exception ex, 
			WebRequest req){
		
		ErrorMessage erroMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(erroMessage, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}	

}

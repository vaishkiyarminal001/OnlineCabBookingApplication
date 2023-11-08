package com.app.exceptions;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(AdminException.class)
		public ResponseEntity<MyErrorDetails> handleAdminEntity(AdminException ex, WebRequest wr){
			MyErrorDetails myErrorDetails = new MyErrorDetails();
			myErrorDetails.setTimeStamp(LocalDate.now());
			myErrorDetails.setMessage(ex.getMessage());
			myErrorDetails.setDiscription(wr.getDescription(false));
			return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);	
			
		}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> handleCustomerEntity(CustomerException ex, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDate.now());
		myErrorDetails.setMessage(ex.getMessage());
		myErrorDetails.setDiscription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(CabException.class)
	public ResponseEntity<MyErrorDetails> handleCabEntity(CabException ex, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDate.now());
		myErrorDetails.setMessage(ex.getMessage());
		myErrorDetails.setDiscription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(Exception.class)
		public ResponseEntity<MyErrorDetails> Exception(Exception ex, WebRequest wr){
			MyErrorDetails myErrorDetails = new MyErrorDetails();
			myErrorDetails.setTimeStamp(LocalDate.now());
			myErrorDetails.setMessage(ex.getMessage());
			myErrorDetails.setDiscription(wr.getDescription(false));
			return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
		}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> handleValidationeException(MethodArgumentNotValidException ex, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDate.now());
		
		myErrorDetails.setMessage("Validation failed !!");
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

		List<String> errorMessages = MethodArgumentNotValidException.errorsToStringList(allErrors);
		

		myErrorDetails.setDiscription(String.join(", ", errorMessages));
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
	}
	}
	



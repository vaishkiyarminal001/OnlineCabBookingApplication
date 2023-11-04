package com.app.exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MyErrorDetails {
	
	private LocalDate timeStamp;
	private String message;
	private String discription;
	
	
	
	
	
	
	public MyErrorDetails(LocalDate timeStamp, String message, String discription) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.discription = discription;
	}
	
	
	public MyErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	
	

}

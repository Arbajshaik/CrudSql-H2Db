package com.example.demo.exception;

import java.util.Date;

public class ErrorDetails {
	private Date Timestamp;
	private String message;
	private String details;
	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		Timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	

}

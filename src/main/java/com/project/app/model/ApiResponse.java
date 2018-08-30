package com.project.app.model;
import java.io.Serializable;

public class ApiResponse implements Serializable{
	 
	private String apiError;
	private String apiWarning;
	private String message;
	
	public String getApiError() {
		return apiError;
	}
	public void setApiError(String apiError) {
		this.apiError = apiError;
	}
	public String getApiWarning() {
		return apiWarning;
	}
	public void setApiWarning(String apiWarning) {
		this.apiWarning = apiWarning;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

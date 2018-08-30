package com.project.app.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class UserDataDTO implements Serializable{
  
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
    private String username;
	
	@JsonProperty
    private String password;
  

   public String getUsername() {
	    return username;
  }

   public void setUsername(String username) {
    this.username = username;
  }

 public String getPassword() {
	return password;
 }

 public void setPassword(String password) {
	this.password = password;
 }

}

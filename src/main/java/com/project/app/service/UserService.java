package com.project.app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.app.model.ApiResponse;
import com.project.app.model.User;
import com.project.app.model.UserDataDTO;
import com.project.app.repository.PermissionRepository;
import com.project.app.repository.UserRepository;




@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
 
  public ResponseEntity<ApiResponse> signin(UserDataDTO data) {
	     
	  User u = new User();
	  u.setPassword(data.getPassword());
	  u.setUsername(data.getUsername());
	  userRepository.save(u);
	    
      ApiResponse response = new ApiResponse();
      response.setMessage("CREATED");
      return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
  }


}

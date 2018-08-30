package com.project.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.ApiResponse;
import com.project.app.model.UserDataDTO;
import com.project.app.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @ResponseBody
  @RequestMapping(value ="/signup", method = RequestMethod.POST) 
  public ResponseEntity<ApiResponse> signup(@RequestBody UserDataDTO user) throws Exception {
	  
	   return userService.signin(user);
  }
}

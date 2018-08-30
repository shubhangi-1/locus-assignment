package com.project.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.ApiResponse;
import com.project.app.model.Permission;
import com.project.app.model.User;
import com.project.app.model.UserDataDTO;
import com.project.app.service.PermissionService;
import com.project.app.service.UserService;


@RestController
@RequestMapping("/access")
public class PermissionsController {
	
	@Autowired
	UserService service;
	
	@Autowired
	PermissionService permissionService;
	
	@ResponseBody
	@RequestMapping(value ="/{reource_id}",method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> signin(@PathVariable("reource_id") Long resourceId,@RequestParam("permission") String permission, 
			@RequestParam("userId") Long userId)  throws Exception{
		
		ApiResponse response = null;
		User user   =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user.getIsSuperAdmin())
		{
			permissionService.grantAccess(userId,resourceId,permission);
		}
		else {
			response = new ApiResponse();
			response.setMessage("YOU ARE NOT AUTHORIZED TO GRANT ACCESS");
			return new ResponseEntity<ApiResponse>(response,HttpStatus.FORBIDDEN);
		}
		return null;
	  }

}

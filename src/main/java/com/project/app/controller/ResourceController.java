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
import com.project.app.model.ResourceInstanceDataDTO;
import com.project.app.model.User;
import com.project.app.service.PermissionService;
import com.project.app.service.ResourceInstanceService;
import com.project.app.service.UserService;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	ResourceInstanceService resourceInstanceService;
	
	@Autowired
	PermissionService permissionService;
	
	private static final String ACCESS_DENIED = "ACCESS_DENIED";
	
	@ResponseBody
	@RequestMapping(value ="/{reource_id}/instance/{resource_instance}",method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getResource(@PathVariable("reource_id") Long resourceId,
			@PathVariable("resource_instance")  Long Id) throws Exception 
	{
		ApiResponse response = new ApiResponse();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	    if(user.getIsSuperAdmin()|| permissionService.hasReadAccess(user.getId(), resourceId))
	    {
	    	   return resourceInstanceService.getResource(Id);
	    }
		else
		{
			response.setApiError(ACCESS_DENIED);
			new ResponseEntity<ApiResponse>(response,HttpStatus.UNAUTHORIZED);
		}
		return null;
    }
	
	@ResponseBody
	@RequestMapping(value ="/{reource_id}/instance/",method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> postResource(@PathVariable("reource_id") Long resourceId,
			@RequestBody ResourceInstanceDataDTO data) throws Exception 
	{
		ApiResponse response = new ApiResponse();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getIsSuperAdmin()|| permissionService.hasWriteAccess(user.getId(), resourceId))
	    {
	    	   return resourceInstanceService.witeResource(data, resourceId);
	    }
		else
		{
			response.setApiError(ACCESS_DENIED);
			new ResponseEntity<ApiResponse>(response,HttpStatus.UNAUTHORIZED);
		}
		return null;
    }
	
	@ResponseBody
	@RequestMapping(value ="/{reource_id}/instance/{resource_instance}",method = RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> deleteResource(@PathVariable("reource_id") Long resourceId,
			@PathVariable("resource_instance")  Long Id) throws Exception 
	{
		ApiResponse response = new ApiResponse();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	    if(user.getIsSuperAdmin()|| permissionService.hasDeleteAccess(user.getId(), resourceId))
	    {
	    	   return resourceInstanceService.deleteResource(Id);
	    }
		else
		{
			response.setApiError(ACCESS_DENIED);
			new ResponseEntity<ApiResponse>(response,HttpStatus.UNAUTHORIZED);
		}
		return null;
    }

}

package com.project.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.app.model.ApiResponse;
import com.project.app.model.Permission;
import com.project.app.model.Permission.Access;
import com.project.app.repository.PermissionRepository;


@Service
public class PermissionService {
	
	@Autowired
	PermissionRepository permissionRepository;
	
	public ResponseEntity<ApiResponse> grantAccess(Long userId,Long resourceId,String access)
	{
		Access permision = null;
		switch(access) {
		case "read":
			permision = Access.READ;
			break;
		case "write":
			permision = Access.WRITE;
		   break;
		case "delete":
		   permision = Access.DELETE;
		   break;
		default:
		   break;
		}
		permissionRepository.grantAccess(userId,resourceId,permision);
		ApiResponse response = new ApiResponse();
		response.setMessage("ACCESS GRANTED");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	public Boolean hasReadAccess(Long userId,Long resourceId)
	{
		Access a = permissionRepository.findAccessbyUserIdAndResourceId(userId, resourceId);
		return a == null ? false : true;
	}
	
	public Boolean hasWriteAccess(Long userId, Long resourceId)
	{
		Access a = permissionRepository.findAccessbyUserIdAndResourceId(userId, resourceId);
		if(a==Access.WRITE || a== Access.DELETE)
			return true;
		return false;
	}
	
	public Boolean hasDeleteAccess(Long userId, Long resourceId)
	{
		Access a = permissionRepository.findAccessbyUserIdAndResourceId(userId, resourceId);
		if(a== Access.DELETE)
			return true;
		return false;
	}
	
	
	

}

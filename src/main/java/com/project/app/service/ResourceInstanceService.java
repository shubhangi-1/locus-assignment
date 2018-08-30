package com.project.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.app.model.ApiResponse;
import com.project.app.model.ResourceInstance;
import com.project.app.model.ResourceInstanceDataDTO;
import com.project.app.repository.ResourceInstanceRepository;
import com.project.app.repository.ResourceRepository;

@Service
public class ResourceInstanceService {
	
	@Autowired
	ResourceInstanceRepository resourceInstanceRepository;
	
	@Autowired
	ResourceRepository resourceRepository;
	
	public ResponseEntity<ApiResponse> getResource(Long id) throws Exception
	{
		ResourceInstance instance =  resourceInstanceRepository.getById(id);
		ApiResponse response = new ApiResponse();
		if(instance != null) {
			response.setMessage(instance.getDescription());
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		}
		else {
			response.setApiWarning("resource not found");
			return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ApiResponse> witeResource(ResourceInstanceDataDTO data, Long resourceId) throws Exception
	{
		ResourceInstance instance = new ResourceInstance();
		instance.setDescription(data.getDescription());
		instance.setResource(resourceRepository.getById(resourceId));
		resourceInstanceRepository.save(instance);
		ApiResponse response = new ApiResponse();
		response.setMessage("CREATED");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
		
	}
	
	
	public ResponseEntity<ApiResponse> deleteResource(Long id) throws Exception
	{
		resourceInstanceRepository.delete(id);
		ApiResponse response = new ApiResponse();
		response.setMessage("DELETED");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
}

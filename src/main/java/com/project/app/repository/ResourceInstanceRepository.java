package com.project.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.app.model.ResourceInstance;

@Transactional
public interface ResourceInstanceRepository extends JpaRepository<ResourceInstance, Long> {
	
	@Query(value="select ID,Description from ResourceInstance where ID=:id",nativeQuery=true)
	public ResourceInstance getById(@Param("id") Long id);

}

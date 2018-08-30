package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.app.model.Resource;

@Transactional
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	@Query(value="select ID,Name from Resource where ID=:id",nativeQuery=true)
	public Resource getById(@Param("id") Long id);

}

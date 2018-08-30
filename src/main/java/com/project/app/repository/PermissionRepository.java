package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.app.model.Permission;
import com.project.app.model.Permission.Access;

@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	
	@Query(value=" select Access from Permission where UserID= :userId and ResourceId = :resourceId", 
			nativeQuery =true)
	public Access findAccessbyUserIdAndResourceId(@Param("userId") Long userId,@Param("resourceId") Long resourceId);
	
	@Modifying
	@Query(value=" Insert into permission(UserId,ResourceId,Access) Values(:userId,:resourceId,:access) on duplicate key update set Access = :access",nativeQuery=true)
	public void grantAccess(@Param("userId") Long userId,@Param("resourceId") Long resourceId, @Param("access") Access access);

}

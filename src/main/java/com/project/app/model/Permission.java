package com.project.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Permission",uniqueConstraints={@UniqueConstraint(columnNames = {"UserId" , "ResourceId"})})
public class Permission {
	
	public enum Access{
		READ,WRITE,DELETE
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="UserId")
	private Long userId;
	
	@Column(name="ResourceId")
	private Long resourceId;
	
	@Enumerated
	@Column(name="Access")
	private Access access;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Access getAcess() {
		return access;
	}

	public void setAcess(Access acess) {
		this.access = acess;
	}

	

}

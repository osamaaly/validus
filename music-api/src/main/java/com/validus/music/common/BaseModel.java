package com.validus.music.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BaseModel {
	@Id
	@GeneratedValue
	private Long id;
	private Date created;
	private Date lastModified;
	
	
	public BaseModel() {
	}
	
	public BaseModel(Long id, Date created, Date lastModified) {
		this.id = id;
		this.created = created;
		this.lastModified = lastModified;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	

}

package com.validus.music.artist;

import java.util.Date;

import javax.persistence.Entity;
import com.validus.music.common.BaseModel;

@Entity
public class Artist extends BaseModel {

	private String name;

	public Artist() {
	}

	public Artist(Long id, Date created, Date lastModified, String name) {
		super(id, created, lastModified);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package com.validus.music.album;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.validus.music.artist.Artist;
import com.validus.music.common.BaseModel;

@Entity
public class Album extends BaseModel{

	private String name;
	private Integer yearReleased;
	@ManyToOne
	private Artist artist;

	public Album() {

	}

	public Album(Long id, Date created, Date lastModified, String name, Integer yearReleased, Artist artist) {
		super(id, created, lastModified);
		this.name = name;
		this.yearReleased = yearReleased;
		this.artist = artist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(Integer yearReleased) {
		this.yearReleased = yearReleased;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}

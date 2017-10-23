package com.validus.music.song;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.validus.music.album.Album;
import com.validus.music.common.BaseModel;

@Entity
public class Song extends BaseModel{

	private Integer track;
	private String name;

	@ManyToOne
	private Album album;

	public Song() {

	}

	public Song(Long id, Date created, Date lastModified, String name, Integer track, Album album) {
		super(id, created, lastModified);
		this.name = name;
		this.track = track;
		this.album = album;
	}

	
	
	public Integer getTrack() {
		return track;
	}

	public void setTrack(Integer track) {
		this.track = track;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	

}

package com.validus.music.album;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepo;

	public List<Album> getAllAlbums(Long artistId) {

		List<Album> albums = new ArrayList<>();
		albumRepo.findByArtistId(artistId).forEach(albums::add);
		return albums;
	}

	public Album getAlbum(Long id) {
		return albumRepo.findOne(id);
	}

	public void addAlbum(Album album) {
		album.setCreated(new Date());
		albumRepo.save(album);
	}

	public void updateAlbum(Album album) {
		album.setLastModified(new Date());
		albumRepo.save(album);
	}
	
	public void deleteAlbum(Long id) {
		albumRepo.delete(id);
	}

}

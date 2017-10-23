package com.validus.music.artist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepo;

	public List<Artist> getAllArtists() {
		List<Artist> artists = new ArrayList<>();
		artistRepo.findAll().forEach(artists::add);
		return artists;
	}

	public Artist getArtist(Long id) {
		return artistRepo.findOne(id);
	}

	public void addArtist(Artist artist) {
		artist.setCreated(new Date());
		artistRepo.save(artist);
	}

	public void updateArtist(Long id, Artist artist) {
		artist.setLastModified(new Date());
		artistRepo.save(artist);
	}
	
	public void deleteArtist(Long id) {
		artistRepo.delete(id);
	}

}

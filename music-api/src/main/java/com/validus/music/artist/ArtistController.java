package com.validus.music.artist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@RequestMapping("/artists")
	List<Artist> getAllArtists() {
		return artistService.getAllArtists();
	}

	@RequestMapping("/artists/{artistId}")
	public Artist getArtist(@PathVariable Long artistId) {
		return artistService.getArtist(artistId);
	}

	@RequestMapping(method=RequestMethod.POST, value="/artists")
	public void addArtist (@RequestBody Artist artist) {
		artistService.addArtist(artist);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/artists/{artistId}")
	public void updateArtist (@RequestBody Artist artist, @PathVariable Long artistId) {
		artistService.updateArtist(artistId, artist);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/artists/{artistId}")
	public void deleteArtist(@PathVariable Long artistId) {
		artistService.deleteArtist(artistId);
	}

}

package com.validus.music.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validus.music.artist.Artist;

@RestController
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@GetMapping("artists/{artistId}/albums")
	List<Album> getAllAlbums(@PathVariable Long artistId) {
		return albumService.getAllAlbums(artistId);
	}

	@GetMapping("artists/{artistId}/albums/{id}")
	public Album getAlbum(@PathVariable Long id) {
		return albumService.getAlbum(id);
	}

	@PostMapping("artists/{artistId}/albums")
	public void createAlbum (@RequestBody Album album, @PathVariable Long artistId) {
		album.setArtist(new Artist(artistId, null, null, ""));
		albumService.addAlbum(album);
	}
	
	@PutMapping("artists/{artistId}/albums/{id}")
	public void updateAlbum (@RequestBody Album album, @PathVariable Long artistId, @PathVariable Long id) {
		album.setArtist(new Artist(artistId, null, null, ""));
		albumService.updateAlbum(album);
	}
	
	@DeleteMapping("artists/{artistId}/albums/{id}")
	public void deleteAlbum(@PathVariable Long id) {
		albumService.deleteAlbum(id);
	}

}

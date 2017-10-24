package com.validus.music.song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validus.music.album.Album;
import com.validus.music.artist.Artist;

@RestController
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping("artists/{artistId}/albums/{albumId}/songs")
	List<Song> getAllSongs(@PathVariable Long albumId) {
		return songService.getAllSongs(albumId);
	}

	@GetMapping("artists/{artistId}/albums/{albumId}/songs/{id}")
	public Song getSong(@PathVariable Long id) {
		return songService.getSong(id);
	}

	@PostMapping("artists/{artistId}/albums/{albumId}/songs")
	public void createSong (@RequestBody Song song, @PathVariable Long artistId, @PathVariable Long albumId) {
		song.setAlbum(new Album(albumId, null, null, "", 0, new Artist(artistId, null, null, "")));
		songService.addSong(song);
	}
	
	@PutMapping("artists/{artistId}/albums/{albumId}/songs/{id}")
	public void updateSong (@RequestBody Song song, @PathVariable Long artistId, @PathVariable Long albumId, @PathVariable Long id) {
		song.setAlbum(new Album(albumId, null, null, "", 0, new Artist(artistId, null, null, "")));
		songService.updateSong(song);
	}
	
	@DeleteMapping("artists/{artistId}/albums/{albumId}/songs/{id}")
	public void deleteSong(@PathVariable Long id) {
		songService.deleteSong(id);
	}

}

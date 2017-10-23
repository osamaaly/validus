package com.validus.music.song;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

	@Autowired
	private SongRepository songRepo;

	public List<Song> getAllSongs(Long albumId) {

		List<Song> songs = new ArrayList<>();
		songRepo.findByAlbumId(albumId).forEach(songs::add);
		return songs;
	}

	public Song getSong(Long id) {
		return songRepo.findOne(id);
	}

	public void addSong(Song song) {
		song.setCreated(new Date());
		songRepo.save(song);
	}

	public void updateSong(Song song) {
		song.setLastModified(new Date());
		songRepo.save(song);
	}
	
	public void deleteSong(Long id) {
		songRepo.delete(id);
	}

}

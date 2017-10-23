package com.validus.music.song;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {

 	public List<Song> findByAlbumId(Long id);
}

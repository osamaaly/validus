package com.validus.music.album;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {

 	public List<Album> findByArtistId(Long id);
}

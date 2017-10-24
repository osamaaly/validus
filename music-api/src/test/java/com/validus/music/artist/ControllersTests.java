package com.validus.music.artist;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.validus.music.album.AlbumRepository;
import com.validus.music.album.AlbumService;
import com.validus.music.artist.ArtistRepository;
import com.validus.music.song.Song;
import com.validus.music.song.SongRepository;
import com.validus.music.song.SongService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ControllersTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ArtistService artistService;

	@MockBean
	AlbumService albumService;

	@MockBean
	SongService songService;

	
	@Test
	public void ArtistServiceTestGetAllArtists() throws Exception {
		
		Mockito.when(artistService.getAllArtists()).thenReturn(
				Collections.emptyList()
		);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getStatus());
		Mockito.verify(artistService).getAllArtists();
	}
	
	@Test
	public void ArtistServiceTestGetArtist() throws Exception {
		
		Long artistId = 1L;
		Mockito.when(artistService.getArtist(artistId)).thenReturn(
				new Artist(artistId, null, null, null)
		);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getStatus());
		Mockito.verify(artistService).getArtist(artistId);
	}

	
	
	
	
	@Test
	public void AlbumServiceTestGetAllAlbums() throws Exception {
		
		Long artistId = 1L;
		Mockito.when(albumService.getAllAlbums(artistId)).thenReturn(
				Collections.emptyList()
		);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getStatus());
		Mockito.verify(albumService).getAllAlbums(artistId);
	}

	@Test
	public void SongServiceTestGetAllSongs() throws Exception {
		
		Long albumId = 2L;
		Mockito.when(songService.getAllSongs(albumId)).thenReturn(
				Collections.emptyList()
		);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums/2/songs")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getStatus());
		Mockito.verify(songService).getAllSongs(albumId);
	}
	
	@Test
	public void SongServiceTestGetSong() throws Exception {
		
		Long songId = 3L;
		Mockito.when(songService.getSong(songId)).thenReturn(
				new Song(songId, null, null, null, null, null)
		);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums/2/songs/3")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getStatus());
		Mockito.verify(songService).getSong(songId);
	}



}

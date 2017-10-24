package com.validus.music;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.validus.music.album.Album;
import com.validus.music.album.AlbumService;
import com.validus.music.artist.Artist;
import com.validus.music.artist.ArtistService;
import com.validus.music.song.Song;
import com.validus.music.song.SongService;

@RunWith(SpringRunner.class)
@WebMvcTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MusicApiUnitTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ArtistService artistService;

	@MockBean
	AlbumService albumService;

	@MockBean
	SongService songService;

	/*
	 * Unit Test Artist RestController Mappings
	 */

	@Test
	public void testCreateArtist() throws Exception {

		Artist mockArtist = new Artist(1L, null, null, "Micael Jackson");

		String inputInJson = this.mapToJson(mockArtist);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/artists").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetArtist() throws Exception {

		Long artistId = 1L;
		Mockito.when(artistService.getArtist(artistId)).thenReturn(new Artist(artistId, null, null, null));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1").accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(artistService).getArtist(artistId);
	}

	@Test
	public void testGetAllArtists() throws Exception {

		Mockito.when(artistService.getAllArtists()).thenReturn(Collections.emptyList());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists").accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(artistService).getAllArtists();
	}

	@Test
	public void testUpdateArtist() throws Exception {

		Artist mockArtist = new Artist(1L, null, null, "Michael Jackson");

		String inputInJson = this.mapToJson(mockArtist);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/artists/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteArtist() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/artists/1").accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(artistService).deleteArtist(1L);
	}

	/*
	 * Unit Test Album RestController Mappings
	 */

	@Test
	public void testCreateAlbum() throws Exception {

		Album mockAlbum = new Album(2L, null, null, "Album-1", 2015, null);

		String inputInJson = this.mapToJson(mockAlbum);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/artists/1/albums")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetAlbum() throws Exception {

		Long albumId = 2L;
		Mockito.when(albumService.getAlbum(albumId)).thenReturn(new Album(2L, null, null, "Album-1", 2015, null));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums/2")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(albumService).getAlbum(albumId);
	}

	@Test
	public void testGetAllAlbums() throws Exception {

		Long artistId = 1L;
		Mockito.when(albumService.getAllAlbums(artistId)).thenReturn(Collections.emptyList());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(albumService).getAllAlbums(artistId);
	}

	@Test
	public void testUpdateAlbum() throws Exception {

		Album mockAlbum = new Album(1L, null, null, "Album - 1 - Modified", 2015, null);

		String inputInJson = this.mapToJson(mockAlbum);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/artists/1/albums/2")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteAlbum() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/artists/1/albums/2")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(albumService).deleteAlbum(2L);
	}

	/*
	 * Unit Test Song Rest Controller Mappings
	 */

	@Test
	public void testCreateSong() throws Exception {

		Song mockSong = new Song(2L, null, null, "song -1", 2015, null);

		String inputInJson = this.mapToJson(mockSong);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/artists/1/albums/2/songs")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetSong() throws Exception {

		Long songId = 3L;
		Mockito.when(songService.getSong(songId)).thenReturn(new Song(songId, null, null, null, null, null));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums/2/songs/3")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(songService).getSong(songId);
	}

	@Test
	public void testGetAllSongs() throws Exception {

		Long albumId = 2L;
		Mockito.when(songService.getAllSongs(albumId)).thenReturn(Collections.emptyList());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/1/albums/2/songs")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(songService).getAllSongs(albumId);
	}

	@Test
	public void testUpdateSong() throws Exception {

		Song mockSong = new Song(3L, null, null, "song -1", 2015, null);

		String inputInJson = this.mapToJson(mockSong);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/artists/1/albums/2/songs/3")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteSong() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/artists/1/albums/2/songs/3")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Mockito.verify(songService).deleteSong(3L);
	}

	
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);

	}
}

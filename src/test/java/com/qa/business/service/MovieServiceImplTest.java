package com.qa.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Movie;
import com.qa.persistence.repository.MovieDBRepository;
import com.qa.persistence.repository.MovieRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

	@InjectMocks
	private MovieServiceImpl service;

	@Mock
	private MovieRepository repo;

	private static final String MOCK_OBJECT = "{\"title\":\"Alien\",\"ageRating\":\"15\",\"poster\":\"Image\"}";

	private static final String MOCK_OBJECT_INVALID = "{\"title\":\"Titanic\",\"ageRating\":\"12\",\"poster\":\"Image\"}";

	@Before
	public void setup() {
	}

	@Test
	public void testGetAllMovies() {
		Mockito.when(repo.getAllMovies()).thenReturn(MOCK_OBJECT);
		Assert.assertEquals(MOCK_OBJECT, service.getAllMovies());
	}

	@Test
	public void testCyclemovies() {

		Mockito.when(repo.cycleMovies("Alien")).thenReturn(1);
		Assert.assertEquals(1, service.cycleMovies("Alien"));

	}

	@Test
	public void testCreateMovieWithValid() {
		Mockito.when(repo.createMovie(MOCK_OBJECT)).thenReturn("{\"message\": \"movie has been sucessfully added\"}");
		String reply = service.addMovie(MOCK_OBJECT);
		Assert.assertEquals("{\"message\": \"movie has been sucessfully added\"}", reply);
	}

	@Test
	public void testCreateMovieWithInvalid() {
		Mockito.when(repo.createMovie(MOCK_OBJECT_INVALID)).thenReturn("Can't Add This Movie");
		String reply = service.addMovie(MOCK_OBJECT_INVALID);
		Assert.assertEquals("Can't Add This Movie", reply);
	}

	@Test
	public void testDeleteMovie() {
		Mockito.when(repo.deleteMovie(1L)).thenReturn("{\"message\": \"movie successfully deleted\"}");
		String reply = service.deleteMovie(1L);
		Assert.assertEquals(reply, "{\"message\": \"movie successfully deleted\"}");
	}

}

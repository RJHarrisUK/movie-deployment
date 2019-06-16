package com.qa.service.repository;

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
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class MovieDBRepositoryTest {

	@InjectMocks
	private MovieDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;


	private static final String MOCK_DATA_ARRAY = "[{\"title\":\"Alien\",\"ageRating\":\"15\",\"poster\":\"Image\"}]";

	private static final String MOCK_OBJECT = "{\"title\":\"Alien\",\"ageRating\":\"15\",\"poster\":\"Image\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllMovies() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("Alien", "15","Image"));
		Mockito.when(query.getResultList()).thenReturn(movies);
		System.out.println(repo.getAllMovies());
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllMovies());
	}

	@Test
	public void testCyclemovies() {

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("Alien", "15","Image"));
		Mockito.when(query.getResultList()).thenReturn(movies);
		Assert.assertEquals(1, repo.cycleMovies("Alien"));

	}

	@Test
	public void testCreateMovie() {
		String reply = repo.createMovie(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"movie has been sucessfully added\"}");
	}

	@Test
	public void testDeleteMovie() {
		String reply = repo.deleteMovie(1L);
		Assert.assertEquals(reply, "{\"message\": \"movie successfully deleted\"}");
	}

}

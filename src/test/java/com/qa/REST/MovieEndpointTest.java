package com.qa.REST;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.MovieService;
import com.qa.rest.MovieEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class MovieEndpointTest {

	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";
	
	private static final Long MOCK_LONG = 0L;

	@InjectMocks
	private MovieEndpoint endpoint;
	// Class under test

	@Mock
	private MovieService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void testGetAllMovies() {
		Mockito.when(service.getAllMovies()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAllMovies());
	}
	
	@Test
	public void testGetAMovie() {
		Mockito.when(service.getAMovie(MOCK_LONG)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAMovie(MOCK_LONG));
	}

	@Test
	public void testCreateMovie() {
		Mockito.when(service.addMovie(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.addMovie(MOCK_VALUE2));
		Mockito.verify(service).addMovie(MOCK_VALUE2);
	}

	@Test
	public void testDeleteMovie() {
		Mockito.when(service.deleteMovie(1L)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.deleteMovie(1L));
		Mockito.verify(service).deleteMovie(1L);
	}
	
	@Test
	public void testUpdateMovie() {
		Mockito.when(service.updateMovie(MOCK_LONG, MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.updateMovie(MOCK_LONG, MOCK_VALUE2));
		Mockito.verify(service).updateMovie(MOCK_LONG, MOCK_VALUE2);
	}

}

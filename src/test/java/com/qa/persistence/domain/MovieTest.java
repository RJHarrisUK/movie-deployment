package com.qa.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {

	private static final String MOCK_DATA_ARRAY = "[{\"title\":\"Alien\",\"ageRating\":\"15\",\"poster\":\"Image\"}]";

	private static final String MOCK_OBJECT = "{\"title\":\"Alien\",\"ageRating\":\"15\",\"poster\":\"Image\"}";

	@Before
	public void setup() {
	}

	@Test
	public void testZeroConstructor() {
		
		Movie movie = new Movie();
		assertEquals(null, movie.getTitle());
	}



}


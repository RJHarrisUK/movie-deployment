package com.qa.persistence.repository;

public interface MovieRepository {

	//C
	String createMovie(String movie);
	
	//R
	String getAllMovies();
	
	String getAMovie(Long id);
	
	//U
	String updateMovie(Long id, String movie);

	//D
	String deleteMovie(Long id);
	
	int cycleMovies(String title);

}
package com.qa.business.service;

public interface MovieService {

	//C
	String addMovie(String movie);
	
	//R
	String getAllMovies();
	
	String getAMovie(Long id);

	//U
	String updateMovie(Long id, String movie);

	//D
	String deleteMovie(Long id);
	
	//Count Movies
	int cycleMovies(String title);

}
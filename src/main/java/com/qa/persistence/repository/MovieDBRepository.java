package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class MovieDBRepository implements MovieRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public int cycleMovies(String title) {

		Query query = manager.createQuery("Select a FROM Movie a");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();

		List<Movie> validList = movies.stream().filter(n -> n.getTitle().equals(title)).collect(Collectors.toList());

		return validList.size();
	}

	@Override
	public String getAllMovies() {
		Query query = manager.createQuery("Select m FROM Movie m");
		Collection<Movie> movies =  (Collection<Movie>) query.getResultList();
		
//		manager.find(Movie.class, 1L);

		return util.getJSONForObject(movies);
	}

	@Override
	@Transactional(REQUIRED)
	public String createMovie(String movie) {
		Movie aMovie = util.getObjectForJSON(movie, Movie.class);
		manager.persist(aMovie);
		return "{\"message\": \"movie has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		
		Movie movieInDB = util.getObjectForJSON(getAMovie(id), Movie.class);

		if (manager.contains(manager.find(Movie.class, id))) {

			manager.remove(manager.find(Movie.class, id));
		}
		return "{\"message\": \"movie successfully deleted\"}";
	}

	@Override
	public String getAMovie(Long id) {
		return util.getJSONForObject(manager.find(Movie.class, id));
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateMovie(Long id, String movie) {

		Movie transMovie = util.getObjectForJSON(movie, Movie.class);

		Movie oldMovie = manager.find(Movie.class, id);

		if (oldMovie != null) {

			oldMovie.setTitle(transMovie.getTitle());
			oldMovie.setAgeRating(transMovie.getAgeRating());
			
			manager.persist(oldMovie);

		}

		return "{\"message\": \"Movie successfully updated\"}";

	}

}

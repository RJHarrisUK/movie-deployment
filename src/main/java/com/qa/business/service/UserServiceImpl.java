package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.repository.MovieRepository;
import com.qa.persistence.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;

	public String getAllUsers() {

		return repo.getAllUsers();
	}

	@Override
	public String addUser(String user) {

		return repo.createUser(user);
	}

	@Override
	public String deleteUser(Long id) {
		return repo.deleteUser(id);
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}


	@Override
	public String getAUser(Long id) {
		return repo.getAUser(id);

	}

	@Override
	public String updateUser(Long id, String user) {
		return repo.updateUser(id, user);
	}

}

package com.qa.persistence.repository;

public interface UserRepository {

	//C
	String createUser(String user);
	
	//R
	String getAllUsers();
	
	String getAUser(Long id);
	
	//U
	String updateUser(Long id, String user);

	//D
	String deleteUser(Long id);

}
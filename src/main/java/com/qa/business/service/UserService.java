package com.qa.business.service;

public interface UserService {

	//C
	String addUser(String user);
	
	//R
	String getAllUsers();
	
	String getAUser(Long id);

	//U
	String updateUser(Long id, String user);

	//D
	String deleteUser(Long id);
	
}
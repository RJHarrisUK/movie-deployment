package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.business.service.MovieService;
import com.qa.business.service.UserService;

@Path("user")
public class UserEndpoint {
	
	@Inject
	private UserService service;

	@Path("getAllUsers")
	@GET
	@Produces({ "application/json" })
	public String getAllUsers() {
		return service.getAllUsers();
	}
	
	@Path("/getAUser/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAUser(@PathParam("id") Long id) {
		return service.getAUser(id);
	}

	@Path("/createUser")
	@POST
	@Produces({ "application/json" })
	public String addUser(String user) {
		return service.addUser(user);
	}

	@Path("/deleteUser/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteUser(@PathParam("id") Long id) {
		return service.deleteUser(id);
	}

	@Path("/updateUser/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateUser(@PathParam("id") Long id, String user) {
		return service.updateUser(id, user);
	}
	
	public void setService(UserService service) {
		this.service = service;
	}

}

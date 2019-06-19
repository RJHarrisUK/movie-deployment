package com.qa.business.service;

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
import com.qa.persistence.repository.MovieRepository;
import com.qa.persistence.repository.UserRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repo;

	private static final String MOCK_OBJECT = "{\"name\":\"Alan\"}";

	@Before
	public void setup() {
	}

	@Test
	public void testGetAllUsers() {
		Mockito.when(repo.getAllUsers()).thenReturn(MOCK_OBJECT);
		Assert.assertEquals(MOCK_OBJECT, service.getAllUsers());
	}


	@Test
	public void testCreateUser() {
		Mockito.when(repo.createUser(MOCK_OBJECT)).thenReturn("{\"message\": \"User has been sucessfully added\"}");
		String reply = service.addUser(MOCK_OBJECT);
		Assert.assertEquals("{\"message\": \"User has been sucessfully added\"}", reply);
	}

	@Test
	public void testDeleteUser() {
		Mockito.when(repo.deleteUser(1L)).thenReturn("{\"message\": \"User successfully deleted\"}");
		String reply = service.deleteUser(1L);
		Assert.assertEquals(reply, "{\"message\": \"User successfully deleted\"}");
	}

}

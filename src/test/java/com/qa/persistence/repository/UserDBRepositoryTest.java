package com.qa.persistence.repository;

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
import com.qa.persistence.domain.User;
import com.qa.persistence.repository.MovieDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserDBRepositoryTest {

	@InjectMocks
	private UserDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"name\":\"Alan\"}]";

	private static final String MOCK_OBJECT = "{\"name\":\"Alan\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllUsers() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<User> users = new ArrayList<User>();
		users.add(new User("Alan"));
		Mockito.when(query.getResultList()).thenReturn(users);
		System.out.println(repo.getAllUsers());
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllUsers());
	}

	@Test
	public void testCreateUser() {
		String reply = repo.createUser(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"User has been sucessfully added\"}");
	}

	@Test
	public void testDeleteUser() {
		String reply = repo.deleteUser(1L);
		Assert.assertEquals(reply, "{\"message\": \"User successfully deleted\"}");
	}

}

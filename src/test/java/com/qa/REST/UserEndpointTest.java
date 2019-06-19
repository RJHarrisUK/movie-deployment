package com.qa.REST;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.MovieService;
import com.qa.business.service.UserService;
import com.qa.rest.MovieEndpoint;
import com.qa.rest.UserEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class UserEndpointTest {

	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";
	
	private static final Long MOCK_LONG = 0L;

	@InjectMocks
	private UserEndpoint endpoint;
	// Class under test

	@Mock
	private UserService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void testGetAllUsers() {
		Mockito.when(service.getAllUsers()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAllUsers());
	}
	
	@Test
	public void testGetAUser() {
		Mockito.when(service.getAUser(MOCK_LONG)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAUser(MOCK_LONG));
	}

	@Test
	public void testCreateUser() {
		Mockito.when(service.addUser(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.addUser(MOCK_VALUE2));
		Mockito.verify(service).addUser(MOCK_VALUE2);
	}

	@Test
	public void testDeleteUser() {
		Mockito.when(service.deleteUser(1L)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.deleteUser(1L));
		Mockito.verify(service).deleteUser(1L);
	}
	
	@Test
	public void testUpdateUser() {
		Mockito.when(service.updateUser(MOCK_LONG, MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.updateUser(MOCK_LONG, MOCK_VALUE2));
		Mockito.verify(service).updateUser(MOCK_LONG, MOCK_VALUE2);
	}

}

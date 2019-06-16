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
import com.qa.persistence.domain.User;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class UserDBRepository implements UserRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllUsers() {
		Query query = manager.createQuery("Select u FROM User u");
		Collection<User> users =  (Collection<User>) query.getResultList();

		return util.getJSONForObject(users);
	}

	@Override
	@Transactional(REQUIRED)
	public String createUser(String user) {
		User aUser = util.getObjectForJSON(user, User.class);
		manager.persist(aUser);
		return "{\"message\": \"User has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteUser(Long id) {
		
		User userInDB = util.getObjectForJSON(getAUser(id), User.class);

		if (manager.contains(manager.find(User.class, id))) {

			manager.remove(manager.find(User.class, id));
		}
		return "{\"message\": \"User successfully deleted\"}";
	}

	@Override
	public String getAUser(Long id) {
		return util.getJSONForObject(manager.find(User.class, id));
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateUser(Long id, String user) {

		User transUser = util.getObjectForJSON(user, User.class);

		User oldUser = manager.find(User.class, id);

		if (oldUser != null) {

			oldUser.setName(transUser.getName());
			
			manager.persist(oldUser);

		}

		return "{\"message\": \"User successfully updated\"}";

	}

}

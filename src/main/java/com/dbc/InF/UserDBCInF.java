package com.dbc.InF;

import java.util.List;

import com.model.User;

public interface UserDBCInF {
	void insert(User user);

	void edit(User user);

	void delete(int id);

	User get(String username);
	
	User get(int id);

	List<User> getAll();

	List<User> search(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
}

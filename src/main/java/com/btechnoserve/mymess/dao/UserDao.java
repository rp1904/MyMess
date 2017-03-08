package com.btechnoserve.mymess.dao;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserRole;

public interface UserDao {

	public User findByUserName(String username);

	public void saveUser(User user);

	public UserRole getUserRoleById(int roleId);

}
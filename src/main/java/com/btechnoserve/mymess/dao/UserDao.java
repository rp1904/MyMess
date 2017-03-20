package com.btechnoserve.mymess.dao;

import java.util.List;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserRole;

public interface UserDao {

	public User findByUserName(String username);

	public void saveUser(User user);

	public UserRole getUserRoleById(int roleId);

	public User getUserByEmail(String emailId);

	public User getUserByMobileNumber(String mobileNumber);

	public User getUserByEmailOrMobileNo(String emailIdRoMobNo);

	public List<User> getAllMembers();

}
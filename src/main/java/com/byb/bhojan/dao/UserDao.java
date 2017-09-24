package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.AppMember;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.model.UserRole;

public interface UserDao {

	public User findByUserName(String username);

	public void saveUser(User user);

	public UserRole getUserRoleById(int roleId);

	public User getUserByEmail(String emailId);

	public User getUserByMobileNumber(String mobileNumber);

	public User getUserByEmailOrMobileNo(String emailIdRoMobNo);

	public List<User> getAllMembers();

	public List<User> getAllMessOwners();

	public User getUserByUserIdPk(String userIdPk);

	public void updateUser(User user);

	public List<User> getMembersByMess(Mess mess, int start, int limit);

	public List<User> getMembersByMessAndSearch(String searchString, Mess mess, int pageNumber, int pageSize);

	public Mess getMessByMember(User loggedInUser);

	public User getUserByUserId(String memberId);

	public List<User> getMembersByMessId(String messId);

	public List<AppMember> getMemberListForAppByMessIdPk(String messIdPk);

	public User getSuperAdminDetails();

}

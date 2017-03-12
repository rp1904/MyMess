package com.btechnoserve.mymess.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.btechnoserve.mymess.dao.UserDao;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserRole;
import com.btechnoserve.mymess.services.UserServices;

@Transactional
@Repository
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}

	@Override
	public UserRole getUserRoleById(int roleId) {
		// TODO Auto-generated method stub
		return userDao.getUserRoleById(roleId);
	}

	@Override
	public User getUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(emailId);
	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return userDao.getUserByMobileNumber(mobileNumber);
	}

	@Override
	public boolean isEmailAlreadyRegistered(String emailId) {
		// TODO Auto-generated method stub
		if (getUserByEmail(emailId) != null)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	@Override
	public boolean isMobileNumberAlreadyRegistered(String mobileNumber) {
		// TODO Auto-generated method stub
		if (getUserByMobileNumber(mobileNumber) != null)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

}

package com.btechnoserve.mymess.services;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserRole;

public interface UserServices {

	public void saveUser(User user);

	public UserRole getUserRoleById(int roleId);

	public User getUserByEmail(String emailId);

	public User getUserByMobileNumber(String mobileNumber);

	public boolean isEmailAlreadyRegistered(String emailId);

	public boolean isMobileNumberAlreadyRegistered(String mobileNumber);
}

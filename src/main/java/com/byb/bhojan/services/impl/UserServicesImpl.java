package com.byb.bhojan.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.AppSessionDao;
import com.byb.bhojan.dao.UserDao;
import com.byb.bhojan.model.AppMember;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.model.UserRole;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.EmailTemplates;
import com.byb.bhojan.util.ProjectConstant;
import com.byb.bhojan.util.RandomStringGenerator;

@Transactional
@Repository
public class UserServicesImpl implements UserServices {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AppSessionDao appSessionDao;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.getUserProfile().setFullName(user.getUserProfile().getFirstName() + " " + user.getUserProfile().getLastName());

		String pass = RandomStringGenerator.getRandomString(5);
		user.setPassword(pass);

		userDao.saveUser(user);

		String msgBody = EmailTemplates.createLoginCredentialsMessage(user.getUserProfile().getFullName(), user.getEmail(), pass);

		emailService.sendEmail(user.getEmail(), ProjectConstant.WELCOME_EMAIL_SUB, msgBody);

		return userDao.getUserByEmail(user.getEmail());
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

	@Override
	public User getUserByEmailOrMobileNo(String emailIdRoMobNo) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmailOrMobileNo(emailIdRoMobNo);
	}

	@Override
	public List<User> getAllMembers() {
		// TODO Auto-generated method stub
		return userDao.getAllMembers();
	}

	@Override
	public User getUserByUserIdPk(String userIdPk) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserIdPk(userIdPk);
	}

	@Override
	public List<User> getMembersByMess(Mess mess, int start, int limit) {
		// TODO Auto-generated method stub
		return userDao.getMembersByMess(mess, start, limit);
	}

	@Override
	public List<User> getMembersByMessAndSearch(String searchString, Mess mess, int start, int limit) {
		// TODO Auto-generated method stub
		return userDao.getMembersByMessAndSearch(searchString, mess, start, limit);
	}

	@Override
	public List<User> getAllMessOwners() {
		// TODO Auto-generated method stub
		return userDao.getAllMessOwners();
	}

	@Override
	public Mess getMessByMember(User loggedInUser) {
		// TODO Auto-generated method stub
		return userDao.getMessByMember(loggedInUser);
	}

	@Override
	public User getMemberByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserId(memberId);
	}

	@Override
	public User getUserByAppKey(String appKey) {
		// TODO Auto-generated method stub
		String userIdPk = appSessionDao.getUserIdPkByAPIkey(appKey);
		if (userIdPk == null) {
			return null;
		}
		return userDao.getUserByUserIdPk(userIdPk);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

		// CreatedUpdated oldCreatedUpdated =
		// getUserByUserIdPk(user.getUserIdPk()).getCreatedUpdated();
		// CreatedUpdated newCreatedUpdated = new
		// CreatedUpdated(oldCreatedUpdated, user.getUserIdPk());
		// user.setCreatedUpdated(newCreatedUpdated);

		userDao.updateUser(user);
	}

	@Override
	public List<User> getMembersByMessId(String messId) {
		// TODO Auto-generated method stub
		return userDao.getMembersByMessId(messId);
	}

	@Override
	public List<AppMember> getMemberListForAppByMessIdPk(String messIdPk) {
		// TODO Auto-generated method stub
		return userDao.getMemberListForAppByMessIdPk(messIdPk);
	}

	@Override
	public User getSuperAdminDetails() {
		// TODO Auto-generated method stub
		return userDao.getSuperAdminDetails();
	}

	@Override
	public long getTotalActiveMemberCount() {
		// TODO Auto-generated method stub
		return userDao.getTotalActiveMemberCount();
	}

	@Override
	public long updateAllPasswords(String pass) {
		// TODO Auto-generated method stub
		return userDao.updateAllPasswords(pass);
	}

}

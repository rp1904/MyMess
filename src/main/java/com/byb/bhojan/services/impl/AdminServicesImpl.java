package com.byb.bhojan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.model.User;
import com.byb.bhojan.dao.AdminDao;
import com.byb.bhojan.services.AdminServices;

@Service
@Transactional
public class AdminServicesImpl implements AdminServices {

	@Autowired
	private AdminDao adminDao;

	@Override
	public List<User> getAllAdmins() {
		// TODO Auto-generated method stub
		return adminDao.getAllAdmins();
	}

}

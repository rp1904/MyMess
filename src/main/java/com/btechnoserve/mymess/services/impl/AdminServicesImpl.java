package com.btechnoserve.mymess.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btechnoserve.mymess.dao.AdminDao;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.AdminServices;

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

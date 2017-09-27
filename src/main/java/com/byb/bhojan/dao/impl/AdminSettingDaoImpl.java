package com.byb.bhojan.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.AdminSettingDao;
import com.byb.bhojan.model.AdminSetting;

@Repository
public class AdminSettingDaoImpl implements AdminSettingDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public AdminSetting getAdminSettings() {
		// TODO Auto-generated method stub
		return (AdminSetting) sessionFactory.getCurrentSession().get(AdminSetting.class, 1);
	}

	@Override
	public void updateAdminSettings(AdminSetting adminSetting) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(adminSetting);
	}

}

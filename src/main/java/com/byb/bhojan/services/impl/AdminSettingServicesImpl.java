package com.byb.bhojan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.AdminSettingDao;
import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.services.AdminSettingServices;

@Service
@Transactional
public class AdminSettingServicesImpl implements AdminSettingServices {

	@Autowired
	private AdminSettingDao adminSettingDao;
	
	@Override
	public AdminSetting getAdminSettings() {
		// TODO Auto-generated method stub
		return adminSettingDao.getAdminSettings();
	}

	@Override
	public AdminSetting updateAdminSettings(AdminSetting adminSetting) {
		// TODO Auto-generated method stub
		adminSettingDao.updateAdminSettings(adminSetting);
		return getAdminSettings();
	}

}

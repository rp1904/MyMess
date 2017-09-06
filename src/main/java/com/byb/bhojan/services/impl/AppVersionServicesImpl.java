package com.byb.bhojan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byb.bhojan.dao.AppVersionDao;
import com.byb.bhojan.model.AppVersion;
import com.byb.bhojan.services.AppVersionServices;

@Service
public class AppVersionServicesImpl implements AppVersionServices {

	@Autowired
	AppVersionDao appVersionDao;

	@Override
	public void saveMessAppVersion(AppVersion appVersion) {
		// TODO Auto-generated method stub
		appVersionDao.saveMessAppVersion(appVersion);
	}

	@Override
	public void saveMemberAppVersion(AppVersion appVersion) {
		// TODO Auto-generated method stub
		appVersionDao.saveMemberAppVersion(appVersion);
	}

	@Override
	public AppVersion getLatestMessAppVersion() {
		// TODO Auto-generated method stub
		return appVersionDao.getLatestMessAppVersion();
	}

	@Override
	public AppVersion getLatestMemberAppVersion() {
		// TODO Auto-generated method stub
		return appVersionDao.getLatestMemberAppVersion();
	}

	@Override
	public List<AppVersion> getAllMessAppVersions() {
		// TODO Auto-generated method stub
		return appVersionDao.getAllMessAppVersions();
	}

	@Override
	public List<AppVersion> getAllMemberAppVersions() {
		// TODO Auto-generated method stub
		return appVersionDao.getAllMemberAppVersions();
	}

}

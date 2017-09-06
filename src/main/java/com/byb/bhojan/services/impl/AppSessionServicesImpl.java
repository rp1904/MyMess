package com.byb.bhojan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byb.bhojan.dao.AppSessionDao;
import com.byb.bhojan.model.AppSession;
import com.byb.bhojan.services.AppSessionServices;

@Service
public class AppSessionServicesImpl implements AppSessionServices {

	@Autowired
	private AppSessionDao appSessionDao;

	@Override
	public void saveOrUpdateAddSession(AppSession appSession) {
		// TODO Auto-generated method stub
		appSessionDao.saveOrUpdateAppSession(appSession);
	}

	@Override
	public String getUserIdPkByAPIkey(String apiKey) {
		// TODO Auto-generated method stub
		return appSessionDao.getUserIdPkByAPIkey(apiKey);
	}

	@Override
	public void deleteAppSessionByApiKey(String apiKey) {
		// TODO Auto-generated method stub
		AppSession appSession = appSessionDao.getAppSessionByAPIKey(apiKey);
		if (appSession != null) {
			appSessionDao.deleteAppSession(appSession);
		}

	}

	@Override
	public void deleteAppSessionByUserId(String userId) {
		// TODO Auto-generated method stub
		AppSession appSession = appSessionDao.getAppSessionByUserId(userId);
		if (appSession != null) {
			appSessionDao.deleteAppSession(appSession);
		}
	}

	@Override
	public String createNewAppSessionForUserId(String userId) {
		// TODO Auto-generated method stub
		deleteAppSessionByUserId(userId);
		AppSession appSession = new AppSession(userId);
		appSessionDao.saveOrUpdateAppSession(appSession);
		return appSession.getApiKey();
	}
}

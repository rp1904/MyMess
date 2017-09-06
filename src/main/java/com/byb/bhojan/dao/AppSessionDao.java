package com.byb.bhojan.dao;

import com.byb.bhojan.model.AppSession;

public interface AppSessionDao {

	public void saveOrUpdateAppSession(AppSession appSession);

	public String getUserIdPkByAPIkey(String apiKey);

	public void deleteAppSession(AppSession appSession);

	public String getAPIkeyForUserId(String userId);

	public AppSession getAppSessionByAPIKey(String apiKey);

	public AppSession getAppSessionByUserId(String userId);
}

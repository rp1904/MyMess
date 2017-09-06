package com.byb.bhojan.services;

import com.byb.bhojan.model.AppSession;

public interface AppSessionServices {

	public void saveOrUpdateAddSession(AppSession appSession);

	public String getUserIdPkByAPIkey(String apiKey);

	public void deleteAppSessionByApiKey(String apiKey);

	public void deleteAppSessionByUserId(String userId);

	public String createNewAppSessionForUserId(String userIdPk);

}

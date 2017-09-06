package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.AppVersion;

public interface AppVersionServices {

	public void saveMessAppVersion(AppVersion appVersion);

	public void saveMemberAppVersion(AppVersion appVersion);

	public AppVersion getLatestMessAppVersion();

	public AppVersion getLatestMemberAppVersion();

	public List<AppVersion> getAllMessAppVersions();

	public List<AppVersion> getAllMemberAppVersions();

}

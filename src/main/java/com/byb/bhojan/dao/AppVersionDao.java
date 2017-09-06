package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.AppVersion;

public interface AppVersionDao {

	public void saveMessAppVersion(AppVersion appVersion);

	public void saveMemberAppVersion(AppVersion appVersion);

	public AppVersion getLatestMessAppVersion();

	public AppVersion getLatestMemberAppVersion();

	public List<AppVersion> getAllMessAppVersions();

	public List<AppVersion> getAllMemberAppVersions();

}

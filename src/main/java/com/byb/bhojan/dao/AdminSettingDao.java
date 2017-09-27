package com.byb.bhojan.dao;

import com.byb.bhojan.model.AdminSetting;

public interface AdminSettingDao {

	public AdminSetting getAdminSettings();

	public void updateAdminSettings(AdminSetting adminSetting);
}

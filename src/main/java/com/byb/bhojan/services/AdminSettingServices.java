package com.byb.bhojan.services;

import com.byb.bhojan.model.AdminSetting;

public interface AdminSettingServices {
	
	public AdminSetting getAdminSettings();
	
	public AdminSetting updateAdminSettings(AdminSetting adminSetting);

}

package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.dto.MessSettingDto;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessSetting;

public interface MessServices {

	public long getTotalActiveMessCount();

	public Mess getActiveMessByMessIdPk(String messIdPk);

	public Mess getActiveMessByMessId(String messId);

	public Mess saveMess(Mess mess);

	public Mess getMessByOwnerIdPk(String userIdPk);

	public boolean isMessNameAlreadyRegistered(String messName);

	public List<Mess> getAllMessess();

	public int updateMessRemainingDays();

	public void updateMess(Mess mess);

	public void saveMessSetting(MessSetting messSetting);

	public void updateMessSetting(MessSettingDto messSettingDto, Mess mess);

	public MessSetting getMessSetting(String messIdFk);

}

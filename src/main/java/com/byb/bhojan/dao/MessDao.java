package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessSetting;

public interface MessDao {

	public Mess getActiveMessByMessId(String messId);

	public Mess saveMess(Mess mess);

	public Mess getActiveMessByMessIdPk(String messIdPk);

	public Mess getMessByOwnerIdPk(String userIdPk);

	public boolean isMessNameAlreadyRegistered(String messName);

	public Mess getMessByMemberIdPk(String userIdPk);

	public List<Mess> getAllMessess();

	public void updateMess(Mess mess);

	public int updateMessRemainingDays();

	public long getTotalActiveMessCount();

	public void saveMessSetting(MessSetting messSetting);

	public void updateMessSetting(MessSetting messSetting);

	public MessSetting getMessSetting(String messIdFk);
}

package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.Mess;

public interface MessServices {

	public int getTotalActiveMessCount();
	
	public Mess getActiveMessByMessIdPk(String messIdPk);

	public Mess getActiveMessByMessId(String messId);

	public Mess saveMess(Mess mess);

	public Mess getMessByOwnerIdPk(String userIdPk);

	public boolean isMessNameAlreadyRegistered(String messName);

	public List<Mess> getAllMessess();
	
	public int updateMessRemainingDays();

	public void updateMess(Mess mess);

}

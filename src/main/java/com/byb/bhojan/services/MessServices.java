package com.byb.bhojan.services;

import com.byb.bhojan.model.Mess;

public interface MessServices {

	public Mess getActiveMessByMessIdPk(String messIdPk);

	public Mess getActiveMessByMessId(String messId);

	public Mess saveMess(Mess mess);

	public Mess getMessByOwnerIdPk(String userIdPk);

	public boolean isMessNameAlreadyRegistered(String messName);

}

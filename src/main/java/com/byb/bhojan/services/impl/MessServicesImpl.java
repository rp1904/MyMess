package com.byb.bhojan.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.MessDao;
import com.byb.bhojan.dto.MessSettingDto;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessSetting;
import com.byb.bhojan.services.MealServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.util.EmailTemplates;
import com.byb.bhojan.util.ProjectConstant;
import com.byb.bhojan.util.RandomStringGenerator;

@Service
@Transactional
public class MessServicesImpl implements MessServices {

	@Autowired
	private MessDao messDao;

	@Autowired
	private MealServices mealServices;

	@Autowired
	private EmailService emailService;

	@Override
	public Mess getActiveMessByMessId(String messId) {
		// TODO Auto-generated method stub
		return messDao.getActiveMessByMessId(messId);
	}

	@Override
	public Mess saveMess(Mess mess) {
		// TODO Auto-generated method stub
		mess.getMessOwner().getUserProfile().setFullName(mess.getMessOwner().getUserProfile().getFirstName() + " " + mess.getMessOwner().getUserProfile().getLastName());

		String pass = RandomStringGenerator.getRandomString(5);
		mess.getMessOwner().setPassword(pass);

		String msgBody = EmailTemplates.createLoginCredentialsMessage(mess.getMessName(), mess.getMessOwner().getEmail(), pass);

		emailService.sendEmail(mess.getMessOwner().getEmail(), ProjectConstant.WELCOME_EMAIL_SUB, msgBody);

		messDao.saveMess(mess);

		Meal meal = new Meal();
		meal.setMess(mess);
		meal.setCreatedUpdated(new CreatedUpdated(mess.getMessIdPk()));
		mealServices.addMeal(meal);

		MessSetting messSetting = new MessSetting();
		messSetting.setCreatedUpdated(new CreatedUpdated(mess.getMessIdPk()));

		Date dt = new Date();

		messSetting.setMess(mess);
		messSetting.setMeal(meal);
		saveMessSetting(messSetting);

		return mess;
	}

	@Override
	public Mess getActiveMessByMessIdPk(String messIdPk) {
		// TODO Auto-generated method stub
		return messDao.getActiveMessByMessIdPk(messIdPk);
	}

	@Override
	public Mess getMessByOwnerIdPk(String userIdPk) {
		// TODO Auto-generated method stub
		return messDao.getMessByOwnerIdPk(userIdPk);
	}

	@Override
	public boolean isMessNameAlreadyRegistered(String messName) {
		// TODO Auto-generated method stub
		return messDao.isMessNameAlreadyRegistered(messName);
	}

	@Override
	public List<Mess> getAllMessess() {
		// TODO Auto-generated method stub
		return messDao.getAllMessess();
	}

	@Override
	public void updateMess(Mess mess) {
		// TODO Auto-generated method stub
		messDao.updateMess(mess);
	}

	@Override
	public int updateMessRemainingDays() {
		// TODO Auto-generated method stub
		return messDao.updateMessRemainingDays();
	}

	@Override
	public long getTotalActiveMessCount() {
		// TODO Auto-generated method stub
		return messDao.getTotalActiveMessCount();
	}

	@Override
	public void saveMessSetting(MessSetting messSetting) {
		// TODO Auto-generated method stub
		messDao.saveMessSetting(messSetting);
	}

	@Override
	public void updateMessSetting(MessSettingDto messSettingDto, Mess mess) {
		// TODO Auto-generated method stub
		MessSetting messSetting = getMessSetting(mess.getMessIdPk());
		messSetting.setCreatedUpdated(new CreatedUpdated(messSetting.getCreatedUpdated(), mess.getMessIdPk()));
		messDao.updateMessSetting(messSetting);
	}

	@Override
	public MessSetting getMessSetting(String messIdFk) {
		// TODO Auto-generated method stub
		return messDao.getMessSetting(messIdFk);
	}

}

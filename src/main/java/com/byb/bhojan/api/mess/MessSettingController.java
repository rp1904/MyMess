package com.byb.bhojan.api.mess;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.dto.MessSettingDto;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessSetting;
import com.byb.bhojan.services.MessServices;

@RestController
@RequestMapping("/api/mess/setting")
public class MessSettingController extends BaseController {

	Logger logger = Logger.getLogger(MessSettingController.class);

	@Autowired
	private MessServices messServices;

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMessSetting(@RequestBody MessSettingDto messSettingDto) {

		Mess mess = getLoggedInMessByAppKey();
		messServices.updateMessSetting(messSettingDto, mess);
		return sendSuccessResponse("Meal added successfully !");
	}

	@RequestMapping(method = RequestMethod.GET)
	public MessSettingDto getMessSetting() {

		Mess mess = getLoggedInMessByAppKey();
		MessSetting messSetting = messServices.getMessSetting(mess.getMessIdPk());

		MessSettingDto messSettingDto = new MessSettingDto();
		messSettingDto.setPreparesNonVeg(messSetting.isPreparesNonVeg());
		messSettingDto.setClosingTime1(messSetting.getClosingTime1());
		messSettingDto.setClosingTime2(messSetting.getClosingTime2());
		messSettingDto.setCreatedAt(messSetting.getCreatedUpdated().getCreatedAt());
		messSettingDto.setCreatedBy(messSetting.getCreatedUpdated().getCreatedBy());
		messSettingDto.setOpeningTime1(messSetting.getOpeningTime1());
		messSettingDto.setOpeningTime2(messSetting.getOpeningTime2());
		messSettingDto.setTrialMealNonVegDefaultMenu(messSetting.getMeal().getNonVegDefaultMenu());
		messSettingDto.setTrialMealNonVegExtra(messSetting.getMeal().getNonVegExtra());
		messSettingDto.setTrialMealNonVegItems(messSetting.getMeal().getNonVegItems());
		messSettingDto.setTrialMealSweet(messSetting.getMeal().getSweet());
		messSettingDto.setTrialMealVegDefaultMenu(messSetting.getMeal().getVegDefaultMenu());
		messSettingDto.setTrialMealVegExtra(messSetting.getMeal().getVegExtra());
		messSettingDto.setTrialMealVegItems(messSetting.getMeal().getVegItems());
		messSettingDto.setUpdatedAt(messSetting.getCreatedUpdated().getUpdatedAt());
		messSettingDto.setUpdatedBy(messSetting.getCreatedUpdated().getUpdatedBy());
		messSettingDto.setWeeklyOff(messSetting.getWeeklyOff());
		messSettingDto.setOffSession1(messSetting.isOffSession1());
		messSettingDto.setOffSession2(messSetting.isOffSession2());

		return messSettingDto;
	}

}

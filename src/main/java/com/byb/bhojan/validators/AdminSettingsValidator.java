package com.byb.bhojan.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.byb.bhojan.model.AdminSetting;

public class AdminSettingsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AdminSetting.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		AdminSetting adminSetting = (AdminSetting) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "freeTrialDays", "NotEmpty.adminSetting.freeTrialDays");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notifyBeforeDays", "NotEmpty.adminSetting.notifyBeforeDays");

		if (adminSetting.getFreeTrialDays() < 1 || adminSetting.getFreeTrialDays() > 30) {
			errors.rejectValue("freeTrialDays", "MinMax.adminSetting.freeTrialDays");
		}
		
		if (adminSetting.getNotifyBeforeDays() < 0 || adminSetting.getNotifyBeforeDays() > 5) {
			errors.rejectValue("notifyBeforeDays", "MinMax.adminSetting.notifyBeforeDays");
		}

	}
}

package com.byb.bhojan.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.util.ProjectConstant;

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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "defaultPayableAmount", "NotEmpty.adminSetting.defaultPayableAmount");

		if (adminSetting.getDefaultPayableAmount() < ProjectConstant.DEFAULT_MIN_AMOUNT) {
			errors.rejectValue("defaultPayableAmount", "Min.adminSetting.defaultPayableAmount", String.valueOf(ProjectConstant.DEFAULT_MIN_AMOUNT));
		}

		if (adminSetting.getDefaultPayableAmount() > ProjectConstant.DEFAULT_MAX_AMOUNT) {
			errors.rejectValue("defaultPayableAmount", "AlreadyExist.user.mobileno", String.valueOf(ProjectConstant.DEFAULT_MAX_AMOUNT));
		}

	}
}

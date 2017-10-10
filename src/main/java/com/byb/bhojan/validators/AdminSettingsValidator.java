package com.byb.bhojan.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.util.ProjectConstant;

public class AdminSettingsValidator implements Validator {

	private Pattern pattern;

	private Matcher matcher;

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

		pattern = Pattern.compile(ProjectConstant.INTEGER_PATTERN);
		matcher = pattern.matcher(String.valueOf(adminSetting.getFreeTrialDays()));
		if (!matcher.matches()) {
			errors.rejectValue("freeTrialDays", "OnlyNumericValueAllowed");
		}

		if (adminSetting.getFreeTrialDays() < 1 || adminSetting.getFreeTrialDays() > 30) {
			errors.rejectValue("freeTrialDays", "MinMax.adminSetting.freeTrialDays");
		}

		if (adminSetting.getNotifyBeforeDays() < 0 || adminSetting.getNotifyBeforeDays() > 5) {
			errors.rejectValue("notifyBeforeDays", "MinMax.adminSetting.notifyBeforeDays");
		}

	}
}

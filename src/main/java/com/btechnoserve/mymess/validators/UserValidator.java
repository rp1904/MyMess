package com.btechnoserve.mymess.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.UserServices;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserServices userServices;

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.user.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.user.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "NotEmpty.user.mobileno");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userInfo.firstName", "NotEmpty.user.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userInfo.lastName", "NotEmpty.user.lastName");

		if (user.getEmail() != null && !user.getEmail().trim().equals("")) {

			if (!emailValidator.valid(user.getEmail())) {
				errors.rejectValue("email", "Pattern.user.email");
			}

			if (userServices.isEmailAlreadyRegistered(user.getEmail())) {
				errors.rejectValue("email", "AlreadyExist.user.email");
			}
		}

		if (user.getMobileNumber() != null && !user.getMobileNumber().trim().equals("")) {

			if (userServices.isMobileNumberAlreadyRegistered(user.getMobileNumber())) {
				errors.rejectValue("mobileNumber", "AlreadyExist.user.mobileno");
			}
		}

		if (user.getPassword() != null && user.getConfirmPassword() != null && !user.getPassword().trim().equals("")
				&& !user.getConfirmPassword().trim().equals("")) {
			if (!user.getPassword().equals(user.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "Diff.user.confirmPassword");
			}
		}

	}

}

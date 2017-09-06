package com.byb.bhojan.validators;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.services.UserServices;

@Component
public class MessValidator implements Validator {

	Logger logger = Logger.getLogger(MessValidator.class);

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserServices userServices;

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Mess.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Mess mess = (Mess) target;

		logger.info("================================================= >>>>>>> In Mess Validator");

		try {
			errors.pushNestedPath("messOwner");
			ValidationUtils.invokeValidator(userValidator, mess.getMessOwner(), errors);

		} finally {
			errors.popNestedPath();
		}

		try {
			errors.pushNestedPath("messOwner");
			ValidationUtils.invokeValidator(userValidator, mess.getMessOwner(), errors);

		} finally {
			errors.popNestedPath();
		}

		logger.info("email: " + (mess.getMessOwner().getEmail()));

		logger.info("Is email alredy registered ? "
				+ userServices.isEmailAlreadyRegistered(mess.getMessOwner().getEmail()));

		// if (mess.getMessOwner().getEmail() != null &&
		// !mess.getMessOwner().getEmail().trim().equals("")) {
		//
		// if (!emailValidator.valid(mess.getMessOwner().getEmail())) {
		// errors.rejectValue("messOwner.email", "Pattern.user.email");
		// }
		//
		// if
		// (userServices.isEmailAlreadyRegistered(mess.getMessOwner().getEmail()))
		// {
		// errors.rejectValue("messOwner.email", "AlreadyExist.user.email");
		// }
		// }
		//
		// if (mess.getMessOwner().getMobileNumber() != null &&
		// !mess.getMessOwner().getMobileNumber().trim().equals("")) {
		//
		// if
		// (userServices.isMobileNumberAlreadyRegistered(mess.getMessOwner().getMobileNumber()))
		// {
		// errors.rejectValue("messOwner.mobileNumber",
		// "AlreadyExist.user.mobileno");
		// }
		// }
		//
		// if (mess.getMessOwner().getPassword() != null &&
		// mess.getMessOwner().getConfirmPassword() != null
		// && !mess.getMessOwner().getPassword().trim().equals("")
		// && !mess.getMessOwner().getConfirmPassword().trim().equals("")) {
		// if
		// (!mess.getMessOwner().getPassword().equals(mess.getMessOwner().getConfirmPassword()))
		// {
		// errors.rejectValue("messOwner.confirmPassword",
		// "Diff.user.confirmPassword");
		// }
		// }

	}

}

package com.byb.bhojan.api.unguarded;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.AdminSettingServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;
import com.byb.bhojan.util.RandomStringGenerator;
import com.byb.bhojan.validators.MessValidator;
import com.byb.bhojan.validators.UserValidator;

@RestController
@RequestMapping("/unguarded/registration")
public class Registration extends BaseController {

	Logger logger = Logger.getLogger(Registration.class);

	@Autowired
	public UserServices userServices;

	@Autowired
	public MessServices messServices;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private MessValidator messValidator;

	@Autowired
	private AdminSettingServices adminSettingServices;

	@InitBinder("member")
	private void memberValidatorInitBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@InitBinder("mess")
	private void messValidatorInitBinder(WebDataBinder binder) {
		binder.setValidator(messValidator);
	}

	@RequestMapping(value = "/member-registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerMember(@RequestBody User member) {

		if (userServices.isEmailAlreadyRegistered(member.getEmail())) {
			return sendErrorResponse("Email Already Registered With Us !");
		}

		if (userServices.isMobileNumberAlreadyRegistered(member.getMobileNumber())) {
			return sendErrorResponse("Mobile Already Registered With Us !");
		}

		member.setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MEMBER));
		member.setCreatedUpdated(new CreatedUpdated(ProjectConstant.CREATEDBY_SELF));
		User savedMember = userServices.saveUser(member);
		logger.info("Saved Member :" + savedMember);

		return sendSuccessResponse("Registration Successful !");
	}

	@RequestMapping(value = "/mess-registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerMess(@RequestBody Mess mess, BindingResult bindingResult) {

		if (messServices.isMessNameAlreadyRegistered(mess.getMessName())) {
			return sendErrorResponse("Mess Name Already Registered With Us !");
		}

		if (userServices.isEmailAlreadyRegistered(mess.getMessOwner().getEmail())) {
			return sendErrorResponse("Email Already Registered With Us !");
		}

		if (userServices.isMobileNumberAlreadyRegistered(mess.getMessOwner().getMobileNumber())) {
			return sendErrorResponse("Mobile Already Registered With Us !");
		}

		mess.setMessId(RandomStringGenerator.getMessCode(mess.getMessName()));
		mess.getMessOwner().setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MESS));
		CreatedUpdated createdUpdated = new CreatedUpdated(ProjectConstant.CREATEDBY_SELF);
		mess.setCreatedUpdated(createdUpdated);
		mess.getMessOwner().setCreatedUpdated(createdUpdated);

		AdminSetting adminSetting = adminSettingServices.getAdminSettings();
		mess.setDaysRemaining(adminSetting.getFreeTrialDays());
		messServices.saveMess(mess);

		return sendSuccessResponse("Registration Successful !");
	}
}

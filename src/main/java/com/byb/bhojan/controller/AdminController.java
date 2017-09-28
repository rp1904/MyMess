package com.byb.bhojan.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.AdminSettingServices;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;

@Controller
@RequestMapping("/web/admin")
public class AdminController {

	Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private MessServices messServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private MemberMealCoupenServices memberMealCoupenServices;

	@Autowired
	private AdminSettingServices adminSettingServices;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView adminHomePage() {

		SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) (SecurityContextHolder.getContext().getAuthentication().getAuthorities()).toArray()[0];

		ModelAndView model = new ModelAndView();

		String role = grantedAuthority.getAuthority();
		String EmailIdOrMobNo = SecurityContextHolder.getContext().getAuthentication().getName();

		logger.info("Role: " + role);
		logger.info("EmailIdOrMobNo: " + EmailIdOrMobNo);

		model.addObject("projectName", ProjectConstant.PROJECT_NAME);

		model.setViewName("super-admin/home");

		return model;

	}

	@RequestMapping(value = "/mess", method = RequestMethod.GET)
	public ModelAndView getMessListPage() {
		return new ModelAndView("super-admin/superadmin-mess");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mess/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject loadMessList() {

		List<Mess> messess = messServices.getAllMessess();

		logger.info("Total Messes: " + messess.size());

		JSONObject a = new JSONObject();
		a.put("data", messess);
		return a;
	}

	@RequestMapping(value = "/mess-members", method = RequestMethod.GET)
	public ModelAndView getMessMemberListPage(@RequestParam("messId") String messId) {

		logger.info(messId);

		Mess mess = messServices.getActiveMessByMessIdPk(messId);

		if (mess == null) {
			ModelAndView modelAndView = new ModelAndView("super-admin/superadmin-mess");
			modelAndView.addObject("error", "invalid_id");
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("super-admin/superadmin-mess-members");

		modelAndView.addObject("messId", messId);
		modelAndView.addObject("messName", mess.getMessName());
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mess-members/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject loadMessMemberList(@RequestParam("messId") String messId) {

		List<User> members = userServices.getMembersByMessId(messId);

		logger.info("Total Members: " + members.size());

		JSONObject a = new JSONObject();
		a.put("data", members);
		return a;
	}

	@RequestMapping(value = "/member-details", method = RequestMethod.POST)
	public ModelAndView getMemberDetailPage(@RequestParam("messId") String messId, @RequestParam("memberId") String memberId) {

		logger.info(memberId);

		User member = userServices.getUserByUserIdPk(memberId);

		if (member == null) {
			return new ModelAndView("redirect:mess-members?messId=" + messId + "&error=invalid_id");
		}

		MemberMealCoupen memberMealCoupen = memberMealCoupenServices.getActiveMealCoupenByMember(member);

		Mess mess = userServices.getMessByMember(member);

		ModelAndView modelAndView = new ModelAndView("super-admin/superadmin-member-details");

		modelAndView.addObject("memberMealCoupen", memberMealCoupen);
		modelAndView.addObject("mess", mess);

		return modelAndView;
	}

	@RequestMapping(value = "/payment-request", method = RequestMethod.GET)
	public ModelAndView getMessPaymentRequestListPage() {
		return new ModelAndView("super-admin/payment-request");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/payment-request/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject loadMessPaymentRequestList() {

		List<Mess> messess = messServices.getAllMessess();

		JSONArray outerObject = new JSONArray();
		for (Mess mess : messess) {
			JSONObject innerObject = new JSONObject();

			innerObject.put("messIdPk", mess.getMessIdPk());
			innerObject.put("messName", mess.getMessName());
			innerObject.put("amount", "<input id='amount_" + mess.getMessIdPk() + "' class='form-control amount' value='100' min='0' max='1000' type='number'/>");
			innerObject.put("status", "pending");

			outerObject.add(innerObject);

		}

		JSONObject a = new JSONObject();
		a.put("data", outerObject);
		return a;
	}

	@RequestMapping(value = "/payment-request", method = RequestMethod.POST)
	public ModelAndView getMessPaymentRequestList(@RequestParam("paymentDetailList") String paymentDetailString) {

		List<String> paymentDetailList = Arrays.asList(paymentDetailString.split("\\s*,\\s*"));
		logger.info("paymentDetailList: " + paymentDetailList);
		for (String paymentDetail : paymentDetailList) {
			logger.info("paymentDetail: " + paymentDetail);
			try {
				String messIdPk = paymentDetail.split("=")[0];
				String amount = paymentDetail.split("=")[1];

				logger.info("messIdPk: " + messIdPk);
				logger.info("amount: " + amount);
			} catch (Exception e) {
				// TODO: handle exception
				ModelAndView modelAndView = new ModelAndView("super-admin/payment-request");
				modelAndView.addObject("error", "invalid_selection");
				modelAndView.addObject("error_msg", "Invalid Selection !");
				return modelAndView;
			}
		}

		return new ModelAndView("super-admin/payment-request");
	}

	@RequestMapping(value = "/admin-settings", method = RequestMethod.GET)
	public ModelAndView getAdminSettingsPage(@ModelAttribute("adminSettings") AdminSetting adminSettings) {

		adminSettings = adminSettingServices.getAdminSettings();

		ModelAndView modelAndView = new ModelAndView("super-admin/admin-settings");
		modelAndView.addObject("adminSettings", adminSettings);

		logger.info(adminSettings);

		return modelAndView;
	}

	@RequestMapping(value = "/admin-settings", method = RequestMethod.POST)
	public ModelAndView updateAdminSettings(@ModelAttribute("adminSettings") AdminSetting adminSettings) {

		AdminSetting oldAdminSettings = adminSettingServices.getAdminSettings();
		oldAdminSettings.setCreatedUpdated(new CreatedUpdated(oldAdminSettings.getCreatedUpdated(), "1"));
		oldAdminSettings.setDefaultPayableAmount(adminSettings.getDefaultPayableAmount());
		adminSettings = adminSettingServices.updateAdminSettings(oldAdminSettings);
		return new ModelAndView("super-admin/admin-settings");
	}

}

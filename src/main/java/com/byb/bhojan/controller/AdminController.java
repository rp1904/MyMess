package com.byb.bhojan.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
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

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView adminHomePage() {

		SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities()).toArray()[0];

		ModelAndView model = new ModelAndView();

		String role = grantedAuthority.getAuthority();
		String EmailIdOrMobNo = SecurityContextHolder.getContext().getAuthentication().getName();

		logger.info("Role: " + role);
		logger.info("EmailIdOrMobNo: " + EmailIdOrMobNo);

		model.addObject("projectName", ProjectConstant.PROJECT_NAME);

		model.setViewName("super-admin/superadmin-home");

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
			return new ModelAndView("redirect:mess?error=invalid_id");
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

	@RequestMapping(value = "/member-details", method = RequestMethod.GET)
	public ModelAndView getMemberDetailPage(@RequestParam("messId") String messId,
			@RequestParam("memberId") String memberId) {

		logger.info(memberId);

		User member = userServices.getUserByUserIdPk(memberId);

		if (member == null) {
			return new ModelAndView("redirect:mess-members?messId=" + messId + "&error=invalid_id");
		}

		MemberMealCoupen memberMealCoupen = memberMealCoupenServices.getMealCoupenByMember(member);

		Mess mess = userServices.getMessByMember(member);

		ModelAndView modelAndView = new ModelAndView("super-admin/superadmin-member-details");

		modelAndView.addObject("memberMealCoupen", memberMealCoupen);
		modelAndView.addObject("mess", mess);

		return modelAndView;
	}

}

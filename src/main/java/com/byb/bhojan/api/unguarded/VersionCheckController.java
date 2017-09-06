package com.byb.bhojan.api.unguarded;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.AppVersion;
import com.byb.bhojan.services.AppVersionServices;

@RestController
@RequestMapping("/unguarded/version-check")
public class VersionCheckController extends BaseController {

	Logger logger = Logger.getLogger(VersionCheckController.class);

	@Autowired
	public AppVersionServices appVersionServices;

	@RequestMapping(value = "/mess", method = RequestMethod.GET)
	public ResponseEntity<?> getMessAppVersion() {

		AppVersion appVersion = appVersionServices.getLatestMessAppVersion();
		return new ResponseEntity<AppVersion>(appVersion, HttpStatus.OK);
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ResponseEntity<?> getMemberAppVersion() {

		AppVersion appVersion = appVersionServices.getLatestMemberAppVersion();
		return new ResponseEntity<AppVersion>(appVersion, HttpStatus.OK);
	}

}

package com.byb.bhojan.api.comman;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.FCMDevice;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.FCMDeviceServices;

@RestController
@RequestMapping("/api/register-device")
public class RegisterDeviceController extends BaseController {

  Logger logger = Logger.getLogger(RegisterDeviceController.class);

  @Autowired
  private FCMDeviceServices FCMdeviceServices;

  @RequestMapping(value = "/member", method = RequestMethod.POST)
  public ResponseEntity<?> registerMemberDevice(@RequestBody FCMDevice fcmDevice) {

    User loggedInUser = getLoggedInUserByAppKey();

    fcmDevice.setUserId(loggedInUser.getUserIdPk());

    FCMDevice oldFcmDevice = FCMdeviceServices.getDeviceDetailsByUserId(fcmDevice.getUserId());

    if (oldFcmDevice != null) {
      fcmDevice.setDeviceId(oldFcmDevice.getDeviceId());
      fcmDevice.setCreatedUpdated(
          new CreatedUpdated(oldFcmDevice.getCreatedUpdated(), fcmDevice.getUserId()));
    } else {
      fcmDevice.setCreatedUpdated(new CreatedUpdated(fcmDevice.getUserId()));
    }

    logger.info(fcmDevice);

    FCMdeviceServices.saveOrUpdateFCMDevice(fcmDevice);

    return sendSuccessResponse("Ready for registration !");
  }

  @RequestMapping(value = "/mess", method = RequestMethod.POST)
  public ResponseEntity<?> registerMessOwnerDeviceDevice(@RequestBody FCMDevice fcmDevice) {

    Mess mess = getLoggedInMessByAppKey();

    fcmDevice.setUserId(mess.getMessOwner().getUserIdPk());

    FCMDevice oldFcmDevice = FCMdeviceServices.getDeviceDetailsByUserId(fcmDevice.getUserId());

    if (oldFcmDevice != null) {
      fcmDevice.setDeviceId(oldFcmDevice.getDeviceId());
      fcmDevice.setCreatedUpdated(
          new CreatedUpdated(oldFcmDevice.getCreatedUpdated(), fcmDevice.getUserId()));
    } else {
      fcmDevice.setCreatedUpdated(new CreatedUpdated(fcmDevice.getUserId()));
    }

    logger.info(fcmDevice);

    FCMdeviceServices.saveOrUpdateFCMDevice(fcmDevice);

    return sendSuccessResponse("Ready for registration !");
  }
}

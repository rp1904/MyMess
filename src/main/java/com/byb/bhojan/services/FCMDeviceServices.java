package com.byb.bhojan.services;

import com.byb.bhojan.model.FCMDevice;

public interface FCMDeviceServices {

	public void saveOrUpdateFCMDevice(FCMDevice fcmDevice);

	public FCMDevice getDeviceDetailsByUserId(String userId);

	public String getFCMtokenByUserId(String userId);

	public String getDevideIdByUserId(String userId);
}

package com.byb.bhojan.dao;

import com.byb.bhojan.model.FCMDevice;

public interface FCMDeviceDao {

	public void saveOrUpdateFCMDevice(FCMDevice fcmDevice);

	public FCMDevice getDeviceDetailsByUserId(String userId);

	public String getFCMtokenByUserId(String userId);

	public String getDevideIdByUserId(String userId);
}

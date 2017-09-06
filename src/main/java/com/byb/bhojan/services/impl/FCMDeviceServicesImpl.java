package com.byb.bhojan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.FCMDeviceDao;
import com.byb.bhojan.model.FCMDevice;
import com.byb.bhojan.services.FCMDeviceServices;

@Service
@Transactional
public class FCMDeviceServicesImpl implements FCMDeviceServices {

	@Autowired
	private FCMDeviceDao FCMdeviceDao;

	@Override
	public void saveOrUpdateFCMDevice(FCMDevice fcmDevice) {
		// TODO Auto-generated method stub
		FCMdeviceDao.saveOrUpdateFCMDevice(fcmDevice);
	}

	@Override
	public FCMDevice getDeviceDetailsByUserId(String userId) {
		// TODO Auto-generated method stub
		return FCMdeviceDao.getDeviceDetailsByUserId(userId);
	}

	@Override
	public String getFCMtokenByUserId(String userId) {
		// TODO Auto-generated method stub
		return FCMdeviceDao.getFCMtokenByUserId(userId);
	}

	@Override
	public String getDevideIdByUserId(String userId) {
		// TODO Auto-generated method stub
		return FCMdeviceDao.getDevideIdByUserId(userId);
	}

}

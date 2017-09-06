package com.byb.bhojan.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.MakePaymentDao;
import com.byb.bhojan.model.MessPayment;
import com.byb.bhojan.services.MakePaymentServices;

@Service
@Transactional
public class MakePaymentServicesImpl implements MakePaymentServices {

	Logger logger = Logger.getLogger(MakePaymentServicesImpl.class);

	@Autowired
	private MakePaymentDao makePaymentDao;

	@Override
	public void makePaymentEntry(MessPayment messPayment) {
		// TODO Auto-generated method stub
		makePaymentDao.makePaymentEntry(messPayment);
	}

	@Override
	public void updateMessPaymentEntry(MessPayment messPayment) {
		// TODO Auto-generated method stub
		makePaymentDao.updateMessPaymentEntry(messPayment);
	}

}

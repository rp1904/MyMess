package com.byb.bhojan.services.impl;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.byb.bhojan.dao.MemberMealCoupenDao;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MemberMealCoupenServices;

@Service
@Transactional
public class MemberMealCoupenServicesImpl implements MemberMealCoupenServices {

  @Autowired
  private MemberMealCoupenDao memberMealCoupenDao;

  @Override
  public void saveMemberMealCoupen(MemberMealCoupen memberMealCoupen) {
    // TODO Auto-generated method stub
    memberMealCoupenDao.saveMemberMealCoupen(memberMealCoupen);
  }

  @Override
  public void updateMemberMealCoupen(MemberMealCoupen memberMealCoupen) {
    // TODO Auto-generated method stub
    memberMealCoupenDao.updateMemberMealCoupen(memberMealCoupen);
  }

  @Override
  public MemberMealCoupen getMealCoupenByMember(User member) {
    // TODO Auto-generated method stub
    return memberMealCoupenDao.getMealCoupenByMember(member);
  }

  @Override
  public int updateExpiredMemberMealCoupen(Date currentDate) {
    // TODO Auto-generated method stub
    return memberMealCoupenDao.updateExpiredMemberMelaCoupen(currentDate);
  }

}

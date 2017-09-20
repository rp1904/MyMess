package com.byb.bhojan.services.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.byb.bhojan.dao.MemberMealDao;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MemberMealServices;

@Service
@Transactional
public class MemberMealServicesImpl implements MemberMealServices {

  @Autowired
  private MemberMealDao memberMealDao;

  @Autowired
  private MemberMealCoupenServices memberMealCoupenServices;

  @Override
  public void saveMemberMeal(MemberMeal memberMeal) {
    // TODO Auto-generated method stub
    memberMealDao.saveMemberMeal(memberMeal);

    MemberMealCoupen memberMealCoupen =
        memberMealCoupenServices.getMealCoupenByMember(memberMeal.getMember());
    memberMealCoupen.setRemainingMealCount(memberMealCoupen.getRemainingMealCount() - 1);
    memberMealCoupen.setCreatedUpdated(new CreatedUpdated(memberMealCoupen.getCreatedUpdated(),
        memberMealCoupen.getCreatedUpdated().getCreatedBy()));
    memberMealCoupenServices.updateMemberMealCoupen(memberMealCoupen);
  }

  @Override
  public boolean isMealAlreadyConsumed(User member, Meal meal) {
    // TODO Auto-generated method stub
    return memberMealDao.isMealAlreadyConsumed(member, meal);
  }

  @Override
  public int getMealCountForMember(User member) {
    // TODO Auto-generated method stub
    return memberMealDao.getMealCountForMember(member);
  }

  @Override
  public List<MemberMeal> getMemberMealsByMealId(String mealId) {
    // TODO Auto-generated method stub
    return memberMealDao.getMemberMealsByMealId(mealId);
  }

  @Override
  public List<MemberMeal> getMemberMealsByMember(User member, int pageNumber, int pageSize) {
    // TODO Auto-generated method stub
    return memberMealDao.getMemberMealsByMember(member, pageNumber, pageSize);
  }

}

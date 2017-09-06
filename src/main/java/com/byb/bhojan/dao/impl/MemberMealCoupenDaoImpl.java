package com.byb.bhojan.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MemberMealCoupenDao;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;
import com.byb.bhojan.util.ProjectConstant;

@Repository
public class MemberMealCoupenDaoImpl implements MemberMealCoupenDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMemberMealCoupen(MemberMealCoupen memberMealCoupen) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(memberMealCoupen);
	}

	@Override
	public void updateMemberMealCoupen(MemberMealCoupen memberMealCoupen) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(memberMealCoupen);
	}

	@Override
	public MemberMealCoupen getMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupen.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("status", ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE));
		return (MemberMealCoupen) criteria.uniqueResult();
	}

	@Override
	public int updateExpiredMemberMelaCoupen(Date currentDate) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hqlUpdate = "UPDATE MemberMealCoupen mmc SET mmc.status = :NEW_STATUS, "
				+ "mmc.createdUpdated.updatedBy = :UPDATED_BY, mmc.createdUpdated.updatedAt = :UPDATED_AT  "
				+ "WHERE mmc.status = :OLD_STATUS AND mmc.expiryDate < :CURRENT_DATE";

		int updatedEntities = session.createQuery(hqlUpdate)
				.setParameter("NEW_STATUS", ProjectConstant.MEAL_COUPEN_STATUS_EXPIRED).setParameter("UPDATED_BY", "1")
				.setParameter("UPDATED_AT", currentDate)
				.setParameter("OLD_STATUS", ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE)
				.setParameter("CURRENT_DATE", currentDate).executeUpdate();

		return updatedEntities;

	}

}

package com.byb.bhojan.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
	public MemberMealCoupen getActiveMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupen.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("status", ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE));
		return (MemberMealCoupen) criteria.uniqueResult();
	}

	@Override
	public MemberMealCoupen getWaitingMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupen.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("status", ProjectConstant.MEAL_COUPEN_STATUS_WAITING));
		return (MemberMealCoupen) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberMealCoupen> getNonWaitingMealCoupensByMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupen.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.ne("status", ProjectConstant.MEAL_COUPEN_STATUS_WAITING));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		return criteria.list();
	}

	@Override
	public MemberMealCoupen getLastExpiredOrConsumedMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupen.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.or(Restrictions.eq("status", ProjectConstant.MEAL_COUPEN_STATUS_EXPIRED), Restrictions.eq("status", ProjectConstant.MEAL_COUPEN_STATUS_CONSUMED)));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		criteria.setMaxResults(1);
		return (MemberMealCoupen) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberMealCoupen> getMealCoupenHistoryByMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupen.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		return criteria.list();
	}

	@Override
	public int updateExpiredMemberMealCoupen(Date updatedAt) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// @formatter:off
		String hqlUpdate = "UPDATE MemberMealCoupen mmc SET mmc.status = :NEW_STATUS, " 
				+ "mmc.createdUpdated.updatedBy = :UPDATED_BY, mmc.createdUpdated.updatedAt = :UPDATED_AT  " 
				+ "WHERE mmc.status = :OLD_STATUS AND mmc.expiryDate < :CURRENT_DATE";
		int updatedEntities = session.createQuery(hqlUpdate).setParameter("NEW_STATUS", ProjectConstant.MEAL_COUPEN_STATUS_EXPIRED)
				.setParameter("UPDATED_BY", "1").setParameter("UPDATED_AT", updatedAt)
				.setParameter("OLD_STATUS", ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE)
				.setParameter("CURRENT_DATE", updatedAt).executeUpdate();
		// @formatter:on

		return updatedEntities;

	}

	@Override
	public int updateWaitingMemberMealCoupen(Date updatedAt) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		// @formatter:off
		String updateQuery = "UPDATE members_mealcoupens mmc SET mmc.status = :NEW_STATUS, " 
				+ "mmc.updated_by = :UPDATED_BY, mmc.updated_at = :UPDATED_AT " 
				+ "WHERE mmc.status = :OLD_STATUS AND mmc.member_id_fk IN ("
				+ "SELECT mmc1.member_id_fk members_mealcoupens mmc1 "
				+ "WHERE mmc1.status = :OLD_STATUS AND mmc1.updated_at = :UPDATED_AT "
				+ ")";
		int updatedEntities = session.createSQLQuery(updateQuery)
				.setParameter("NEW_STATUS", ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE)
				.setParameter("UPDATED_BY", "1").setParameter("UPDATED_AT", updatedAt)
				.setParameter("OLD_STATUS", ProjectConstant.MEAL_COUPEN_STATUS_WAITING)
				.executeUpdate();
		// @formatter:on

		return updatedEntities;
	}

}

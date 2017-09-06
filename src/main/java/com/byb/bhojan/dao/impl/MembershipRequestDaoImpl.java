package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MembershipRequestDao;
import com.byb.bhojan.model.MembershipRequest;
import com.byb.bhojan.util.ProjectConstant;

@Repository
public class MembershipRequestDaoImpl implements MembershipRequestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MembershipRequest> getMembershipRequestsByMessId(String messId) {
		// TODO Auto-generated method stub
		List<MembershipRequest> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MembershipRequest.class);
		criteria.createAlias("mess", "m").add(Restrictions.eq("m.messId", messId.trim()));
		result = criteria.list();
		return result;
	}

	@Override
	public MembershipRequest getMembershipRequestByUserIdAndMessId(String userId, String messId) {
		// TODO Auto-generated method stub
		MembershipRequest result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MembershipRequest.class);
		criteria.createAlias("mess", "m").add(Restrictions.eq("m.messId", messId.trim()));
		criteria.createAlias("member", "u").add(Restrictions.eq("u.userIdPk", userId.trim()));
		result = (MembershipRequest) criteria.uniqueResult();
		return result;
	}

	@Override
	public int saveMembershipRequests(MembershipRequest membershipRequest) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		if (session.save(membershipRequest) != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public MembershipRequest getMembershipRequestByUserId(String userIdPk) {
		// TODO Auto-generated method stub
		MembershipRequest result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MembershipRequest.class).createAlias("member", "u");
		criteria.add(Restrictions.eq("u.userIdPk", userIdPk.trim()));
		result = (MembershipRequest) criteria.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MembershipRequest> getPendingMembershipRequestsByMessIdPk(String messIdPk) {
		// TODO Auto-generated method stub
		List<MembershipRequest> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MembershipRequest.class).createAlias("mess", "m");
		criteria.add(Restrictions.eq("m.messIdPk", messIdPk.trim()));
		criteria.add(Restrictions.eq("requestStatus", ProjectConstant.MEMBERSHIP_REQUEST_PENDING));
		result = criteria.list();
		return result;
	}

	@Override
	public MembershipRequest getPendingMembershipRequestByMessIdPkAndMemberIdPk(String messIdPk, String memberIdPk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MembershipRequest.class);
		criteria.createAlias("member", "u");
		criteria.add(Restrictions.eq("u.userIdPk", memberIdPk.trim()));
		criteria.createAlias("mess", "m");
		criteria.add(Restrictions.eq("m.messIdPk", messIdPk.trim()));
		criteria.add(Restrictions.eq("requestStatus", ProjectConstant.MEMBERSHIP_REQUEST_PENDING));
		return (MembershipRequest) criteria.uniqueResult();

	}

	@Override
	public MembershipRequest updateMembershipRequest(MembershipRequest membershipRequest) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(membershipRequest);
		return membershipRequest;

	}

}

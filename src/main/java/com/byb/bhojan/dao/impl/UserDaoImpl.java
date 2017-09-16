package com.byb.bhojan.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.byb.bhojan.dao.UserDao;
import com.byb.bhojan.model.AppMember;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.model.UserRole;
import com.byb.bhojan.util.ProjectConstant;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  public User findByUserName(String emailOrMobNo) {

    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    Criterion criterion = Restrictions.or(Restrictions.eq("email", emailOrMobNo),
        Restrictions.eq("mobileNumber", emailOrMobNo));
    criteria.add(criterion);
    return (User) criteria.uniqueResult();
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void saveUser(User user) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));

    session.save(user);
  }

  @Override
  public UserRole getUserRoleById(int roleId) {
    // TODO Auto-generated method stub
    return (UserRole) sessionFactory.getCurrentSession().get(UserRole.class, roleId);
  }

  @Override
  public User getUserByEmail(String emailId) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("email", emailId));
    User m = (User) criteria.uniqueResult();
    return m;
  }

  @Override
  public User getUserByMobileNumber(String mobileNumber) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("mobileNumber", mobileNumber));
    User m = (User) criteria.uniqueResult();
    return m;
  }

  @Override
  public User getUserByEmailOrMobileNo(String emailIdRoMobNo) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    Criterion mobNo = Restrictions.eq("mobileNumber", emailIdRoMobNo);
    Criterion email = Restrictions.eq("email", emailIdRoMobNo);
    criteria.add(Restrictions.or(mobNo, email));
    User m = (User) criteria.uniqueResult();
    return m;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getAllMembers() {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("userRole.userRoleId", ProjectConstant.USER_ROLE_ID_MEMBER));
    return (List<User>) criteria.list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getAllMessOwners() {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("userRole.userRoleId", ProjectConstant.USER_ROLE_ID_MESS));
    return (List<User>) criteria.list();
  }

  @Override
  public User getUserByUserIdPk(String userIdPk) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("userIdPk", userIdPk));
    User m = (User) criteria.uniqueResult();
    return m;
  }

  @Override
  public void updateUser(User user) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    session.update(user);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getMembersByMess(Mess mess, int pageNumber, int pageSize) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.setFirstResult((pageNumber - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    criteria.add(Restrictions.eq("mess", mess));
    criteria.addOrder(Order.desc("createdUpdated.updatedAt"));

    return criteria.list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getMembersByMessAndSearch(String searchString, Mess mess, int pageNumber,
      int pageSize) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.setFirstResult((pageNumber - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    criteria.add(Restrictions.eq("mess", mess));
    criteria.createAlias("userProfile", "up")
        .add(Restrictions.ilike("up.fullName", searchString.toLowerCase(), MatchMode.START));
    criteria.addOrder(Order.asc("up.fullName"));

    return criteria.list();
  }

  @Override
  public Mess getMessByMember(User loggedInUser) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Mess.class);
    criteria.createAlias("messMembers", "u")
        .add(Restrictions.eq("u.userIdPk", loggedInUser.getUserIdPk()));

    return (Mess) criteria.uniqueResult();
  }

  @Override
  public User getUserByUserId(String userIdPk) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("userIdPk", userIdPk));
    User m = (User) criteria.uniqueResult();
    return m;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getMembersByMessId(String messIdPk) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.createAlias("mess", "m").add(Restrictions.eq("m.messIdPk", messIdPk));
    criteria.addOrder(Order.desc("createdUpdated.updatedAt"));

    return criteria.list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<AppMember> getMemberListForAppByMessIdPk(String messIdPk) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query =
        session.createSQLQuery("SELECT u.user_id_pk, up.full_name, mm.created_at FROM users as u "
            + "INNER JOIN user_profiles up ON up.user_profile_id_pk = u.user_profile_id_fk "
            + "INNER JOIN messes as m ON m.mess_id_pk = u.mess_id_fk "
            + "INNER JOIN meals as ml ON ml.mess_id = m.mess_id_pk AND ml.status = 'OPEN' "
            + "LEFT JOIN members_meals as mm ON u.user_id_pk = mm.member_id_fk" + " WHERE "
            + "m.mess_id_pk =:MESS_ID_PK ORDER BY mm.created_at DESC, up.full_name");
    query.setParameter("MESS_ID_PK", messIdPk);

    List<AppMember> mList = new ArrayList<AppMember>();
    List<Object> l = query.list();
    Iterator<Object> it = l.iterator();
    while (it.hasNext()) {
      Object row[] = (Object[]) it.next();
      System.out.println("--------------->>> " + row[0] + " -- " + row[1] + " -- " + row[2]);
      AppMember appMember = new AppMember();
      appMember.setMemberId((String) row[0]);
      appMember.setFullName((String) row[1]);
      appMember.setCreatedAt((Date) (row[2]));
      mList.add(appMember);
    }
    System.out.println("--------------->>> " + mList);
    return mList;
  }
}

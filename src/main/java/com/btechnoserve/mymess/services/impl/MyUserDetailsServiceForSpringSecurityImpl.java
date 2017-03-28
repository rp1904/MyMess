package com.btechnoserve.mymess.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.btechnoserve.mymess.dao.UserDao;
import com.btechnoserve.mymess.model.UserRole;

@Transactional
public class MyUserDetailsServiceForSpringSecurityImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		com.btechnoserve.mymess.model.User user = userDao.findByUserName(username);

		String password = user.getPassword();
		boolean enabled = user.isEnable();
		boolean accoutNonExpired = user.isAccoutNonExpired();
		boolean credentialsNonExpired = user.isCredentialsNonExpired();
		boolean accoutNonLocker = user.isAccoutNonLocker();
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return new User(username, password, enabled, accoutNonExpired, credentialsNonExpired, accoutNonLocker,
				authorities);

	}

	private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
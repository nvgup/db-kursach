package ua.com.kpi.kursach.web.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ua.com.kpi.kursach.dao.UserDao;
import ua.com.kpi.kursach.dto.UserDTO;
import ua.com.kpi.kursach.dto.UserType;

@Component
public class LoginUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserDTO user = userDao.getUserByLogin(login);

		if (user == null)
			throw new UsernameNotFoundException("User with login '" + login + "' not fount");

		return new User(login, user.getPassword(), Arrays.asList(
				new SimpleGrantedAuthority("ROLE_"+UserType.getTypeByDBValue(user.getType()).toString().toUpperCase())));
	}

}

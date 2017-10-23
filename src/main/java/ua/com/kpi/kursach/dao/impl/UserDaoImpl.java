package ua.com.kpi.kursach.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.kpi.kursach.dao.UserDao;
import ua.com.kpi.kursach.dao.mappers.UserRowMapper;
import ua.com.kpi.kursach.dto.UserDTO;

@Component
public class UserDaoImpl implements UserDao {
	private static final String USER_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login=?";
	private static final String ALL_USERS_QUERY = "SELECT * FROM users";
	private static final String ALL_TYPE_USERS_QUERY = "SELECT * FROM users where type=?";
	private static final String DELETE_USER_BY_LOGIN_QUERY = "DELETE FROM users	WHERE login=?";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET first_name=?, last_name=?, sur_name=?, phone=?, email=?, faculty_group=? WHERE login=?";
	private static final String UPDATE_USER_CONTACT_INFO_QUERY = "UPDATE users SET phone=?, email=? WHERE login=?";
	private static final String UPDATE_PASSWORD_INFO_QUERY = "UPDATE users SET password=? WHERE login=?";
	private static final String CREATE_USER_QUERY = "INSERT INTO users(login, password, type, last_name, first_name, sur_name, phone, email, faculty_group) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserRowMapper userRowMapper;
	
	@Override
	public UserDTO getUserByLogin(String login) {
		try {
			return jdbcTemplate.queryForObject(USER_BY_LOGIN_QUERY, new Object[] { login }, userRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return jdbcTemplate.query(ALL_USERS_QUERY, userRowMapper);
	}

	@Override
	public List<UserDTO> getAllUsersByType(String type) {
		return jdbcTemplate.query(ALL_TYPE_USERS_QUERY, new Object[] { type }, userRowMapper);
	}

	@Override
	public void removeUserByLogin(String login) {
		jdbcTemplate.update(DELETE_USER_BY_LOGIN_QUERY, new Object[] { login });
	}

	@Override
	public void updateUser(UserDTO user) {
		Object[] userData = new Object[] { user.getFirstName(), user.getLastName(), user.getSurName(), user.getPhone(),
				user.getEmail(), user.getFacultyName(), user.getLogin() };

		jdbcTemplate.update(UPDATE_USER_QUERY, userData);
	}

	@Override
	public void updateUserContactInfo(UserDTO user) {
		Object[] userData = new Object[] { user.getPhone(), user.getEmail(), user.getLogin() };
		jdbcTemplate.update(UPDATE_USER_CONTACT_INFO_QUERY, userData);
	}

	@Override
	public void updateUserPassword(UserDTO user) {
		Object[] userData = new Object[] { user.getPassword(), user.getLogin() };
		jdbcTemplate.update(UPDATE_PASSWORD_INFO_QUERY, userData);
	}

	@Override
	public String createUser(UserDTO user) {
		Object[] userData = new Object[] { user.getLogin(), user.getPassword(), user.getType(), user.getLastName(),
				user.getFirstName(), user.getSurName(), user.getPhone(), user.getEmail(), user.getFacultyName() };

		jdbcTemplate.update(CREATE_USER_QUERY, userData);
		return user.getLogin();
	}

	@Override
	public boolean isUserExist(String login) {
		return getUserByLogin(login) != null;
	}


}

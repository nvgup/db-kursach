package ua.com.kpi.kursach.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.kpi.kursach.dto.UserDTO;
@Component
public class UserRowMapper implements RowMapper<UserDTO> {
	private static final String DB_LOGIN_FIELD = "login";
	private static final String DB_PASSWORD_FIELD = "password";
	private static final String DB_FIRST_NAME_FIELD = "first_Name";
	private static final String DB_LAST_NAME_FIELD = "last_name";
	private static final String DB_SUR_NAME = "sur_name";
	private static final String DB_TYPE_FIELD = "type";
	private static final String DB_PHONE_FIELD = "phone";
	private static final String DB_EMAIL_FIELD = "email";
	private static final String DB_FACULTY_FIELD = "faculty_group";

	@Override
	public UserDTO mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		UserDTO user = new UserDTO();
		user.setLogin(resultSet.getString(DB_LOGIN_FIELD));
		user.setPassword(resultSet.getString(DB_PASSWORD_FIELD));
		user.setType(resultSet.getString(DB_TYPE_FIELD));
		user.setFirstName(resultSet.getString(DB_FIRST_NAME_FIELD));
		user.setLastName(resultSet.getString(DB_LAST_NAME_FIELD));
		user.setSurName(resultSet.getString(DB_SUR_NAME));
		user.setPhone(resultSet.getString(DB_PHONE_FIELD));
		user.setEmail(resultSet.getString(DB_EMAIL_FIELD));
		user.setFacultyName(resultSet.getString(DB_FACULTY_FIELD));
		return user;
	}
}
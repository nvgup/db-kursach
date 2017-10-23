package ua.com.kpi.kursach.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.kpi.kursach.dao.FacultyGroupDao;

@Component
public class FacultyGroupDaoImpl implements FacultyGroupDao {
	private static final String ALL_GROUPS_QUERY = "SELECT name FROM groups";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<String> getAllGroups() {
		return jdbcTemplate.queryForList(ALL_GROUPS_QUERY, String.class);
	}

}

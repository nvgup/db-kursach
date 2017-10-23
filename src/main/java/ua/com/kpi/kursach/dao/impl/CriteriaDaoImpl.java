package ua.com.kpi.kursach.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.kpi.kursach.dao.CriteriaDao;
import ua.com.kpi.kursach.dao.mappers.CriteriaRowMapper;
import ua.com.kpi.kursach.dto.CriteriaDTO;

@Component
public class CriteriaDaoImpl implements CriteriaDao {
	private static final String ALL_CRITERIAS_QUERY = "Select * From criterias";
	private static final String ALL_CRITERIAS_TITLES_QUERY = "Select title From criterias";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CriteriaRowMapper criteriaRowMapper;

	@Override
	public List<CriteriaDTO> getAllCriterias() {
		return jdbcTemplate.query(ALL_CRITERIAS_QUERY, criteriaRowMapper);
	}

	@Override
	public List<String> getAllCriteriaTitles() {
		return jdbcTemplate.queryForList(ALL_CRITERIAS_TITLES_QUERY, String.class);
	}; 

	
}

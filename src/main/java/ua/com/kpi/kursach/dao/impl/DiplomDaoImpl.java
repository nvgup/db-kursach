package ua.com.kpi.kursach.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kpi.kursach.dao.DiplomDao;
import ua.com.kpi.kursach.dao.mappers.CriteriaMarkRowMapper;
import ua.com.kpi.kursach.dao.mappers.DiplomWithOwnerRowMapper;
import ua.com.kpi.kursach.dto.DiplomDTO;
import ua.com.kpi.kursach.dto.CriteriaMarkDTO;

@Component
public class DiplomDaoImpl implements DiplomDao {
	private static final String DIPLOM_BY_OWNER_QUERY = "Select * From diploms JOIN users ON diploms.owner=users.login Where diploms.owner=?";
	private static final String CREATE_DIPLOM_QUERY = "INSERT INTO diploms(owner, name, file_path) VALUES (?, ?, ?)";
	private static final String DIPLOMS_BY_FACULRY_QUERY = "Select * From diploms JOIN users ON diploms.owner=users.login Where users.faculty_group=?";
	private static final String ALL_DIPLOMS_QUERY = "Select * From diploms JOIN users ON diploms.owner=users.login";

	private static final String PUT_DIPLOM_MARK_QUERY = "INSERT INTO EVALUATIONS(diplom, teacher, criteria, points) VALUES (?, ?, ?, ?)";
	private static final String TEACHER_DIPLOM_MARK_QUERY = "Select criteria, points FROM EVALUATIONS WHERE diplom=? and teacher=?";
	private static final String TEACHERS_WHO_VERIFY_DIPLOM_QUERY = "Select teacher FROM EVALUATIONS WHERE diplom=? GROUP By teacher";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DiplomWithOwnerRowMapper diplomWithOwnerRowMapper;
	@Autowired
	private CriteriaMarkRowMapper criteriaMarkRowMapper;

	@Override
	public DiplomDTO getDiplomByOwner(String ownerId) {
		try {
			return jdbcTemplate.queryForObject(DIPLOM_BY_OWNER_QUERY, new Object[] { ownerId }, diplomWithOwnerRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public String createDiplom(DiplomDTO diplom) {
		Object[] diplomData = new Object[] { diplom.getOwner().getLogin(), diplom.getName(), diplom.getFilePath() };

		jdbcTemplate.update(CREATE_DIPLOM_QUERY, diplomData);
		return diplom.getOwner().getLogin();
	}

	@Override
	public List<DiplomDTO> getDiplomsByFaculty(String facultyName) {
		return jdbcTemplate.query(DIPLOMS_BY_FACULRY_QUERY, new Object[] { facultyName }, diplomWithOwnerRowMapper);
	}

	@Override
	public List<DiplomDTO> getAllDiploms() {
		return jdbcTemplate.query(ALL_DIPLOMS_QUERY, diplomWithOwnerRowMapper);
	}

	@Override
	@Transactional
	public void putDiplomMarks(String diplom, String teacher, List<CriteriaMarkDTO> criteriaPoints) {
		for (CriteriaMarkDTO criteriaMark : criteriaPoints) {
			Object[] args = new Object[] {diplom, teacher, criteriaMark.getCriteria(), criteriaMark.getPoints()};
			jdbcTemplate.update(PUT_DIPLOM_MARK_QUERY, args);
		}
		
		
	}

	@Override
	public List<CriteriaMarkDTO> getTeacherDiplomMarks(String diplom, String teacher) {
		return jdbcTemplate.query(TEACHER_DIPLOM_MARK_QUERY, new Object[] { diplom, teacher }, criteriaMarkRowMapper);
	}
	
	@Override
	public Map<String, List<CriteriaMarkDTO>> getDiplomMarks(String diplom) {
		List<String> verifiedTeachers = jdbcTemplate.queryForList(TEACHERS_WHO_VERIFY_DIPLOM_QUERY, new Object[] { diplom}, String.class);
		
		Map<String, List<CriteriaMarkDTO>> allMarks = new HashMap<>();
		
		for (String teacher: verifiedTeachers) 
			allMarks.put(teacher, getTeacherDiplomMarks(diplom, teacher));
		
		return allMarks;
	}

}

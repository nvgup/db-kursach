package ua.com.kpi.kursach.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.kpi.kursach.dto.CriteriaMarkDTO;

@Component
public class CriteriaMarkRowMapper implements RowMapper<CriteriaMarkDTO> {

	private static final String TITLE_FIELD = "criteria";
	private static final String MAX_POINT_FIELD = "points";

	@Override
	public CriteriaMarkDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return  new CriteriaMarkDTO(resultSet.getString(TITLE_FIELD), resultSet.getInt(MAX_POINT_FIELD));
	}

}

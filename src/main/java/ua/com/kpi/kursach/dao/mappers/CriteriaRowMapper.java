package ua.com.kpi.kursach.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.kpi.kursach.dto.CriteriaDTO;

@Component
public class CriteriaRowMapper implements RowMapper<CriteriaDTO> {

	private static final String TITLE_FIELD = "title";
	private static final String MAX_POINT_FIELD = "max_point";

	@Override
	public CriteriaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		CriteriaDTO criteria = new CriteriaDTO();
		criteria.setTitle(resultSet.getString(TITLE_FIELD));
		criteria.setMaxPoint(resultSet.getInt(MAX_POINT_FIELD));
		return criteria;
	}

}

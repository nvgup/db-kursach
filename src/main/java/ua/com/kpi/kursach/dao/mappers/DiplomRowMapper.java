package ua.com.kpi.kursach.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.kpi.kursach.dto.DiplomDTO;

@Component
public class DiplomRowMapper implements RowMapper<DiplomDTO> {

	private static final String NAME_FIELD = "name";
	private static final String FILE_PATH_FIELD = "file_path";

	@Override
	public DiplomDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DiplomDTO diplom = new DiplomDTO();
		diplom.setName(resultSet.getString(NAME_FIELD));
		diplom.setFilePath(resultSet.getString(FILE_PATH_FIELD));
		return diplom;
	}

}

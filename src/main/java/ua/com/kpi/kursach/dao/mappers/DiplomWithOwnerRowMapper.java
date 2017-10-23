package ua.com.kpi.kursach.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.kpi.kursach.dto.DiplomDTO;
import ua.com.kpi.kursach.dto.UserDTO;

@Component
public class DiplomWithOwnerRowMapper implements RowMapper<DiplomDTO> {

	@Autowired
	private DiplomRowMapper diplomRowMapper;
	@Autowired 
	private UserRowMapper userRowMapper;
	
	@Override
	public DiplomDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DiplomDTO diplom = diplomRowMapper.mapRow(resultSet, rowNum);
		UserDTO user = userRowMapper.mapRow(resultSet, rowNum);
		
		diplom.setOwner(user);
		return diplom;
	}

}

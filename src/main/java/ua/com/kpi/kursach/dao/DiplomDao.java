package ua.com.kpi.kursach.dao;

import java.util.List;
import java.util.Map;

import ua.com.kpi.kursach.dto.DiplomDTO;
import ua.com.kpi.kursach.dto.CriteriaMarkDTO;

public interface DiplomDao {

	DiplomDTO getDiplomByOwner(String ownerId);

	String createDiplom(DiplomDTO diplom);
	
	List<DiplomDTO> getDiplomsByFaculty(String facultyName);
	
	List<DiplomDTO> getAllDiploms();
	
	void putDiplomMarks(String diplom, String teacher, List<CriteriaMarkDTO> criteriaPoints);
	
	List<CriteriaMarkDTO> getTeacherDiplomMarks(String diplom, String teacher);

	Map<String, List<CriteriaMarkDTO>> getDiplomMarks(String diplom);
}

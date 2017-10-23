package ua.com.kpi.kursach.dao;

import java.util.List;

import ua.com.kpi.kursach.dto.CriteriaDTO;

public interface CriteriaDao {

	List<CriteriaDTO> getAllCriterias(); 
	List<String> getAllCriteriaTitles(); 
}

package ua.com.kpi.kursach.dto;

public class CriteriaMarkDTO {
	private String criteria;
	private Integer points;

	public CriteriaMarkDTO(String criteria, Integer points) {
		this.criteria = criteria;
		this.points = points;
	}

	public String getCriteria() {
		return criteria;
	}
 
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer point) {
		this.points = point;
	}

}

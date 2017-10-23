package ua.com.kpi.kursach.dto;

public class CriteriaDTO {
	private String title;
	private Integer maxPoint;
	
	public Integer getMaxPoint() {
		return maxPoint;
	}
	public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}

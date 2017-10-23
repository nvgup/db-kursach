package ua.com.kpi.kursach.dto;

public enum UserType {
	STUDENT("студент"), 
	TEACHER("викладач"), 
	ADMIN("адмін");
	
	private String dbValue;
	
	private UserType(String dbValue) {
		this.dbValue = dbValue;
	} 
	
	public static UserType getTypeByDBValue(String dbValue) {
		for (UserType type : values())
			if (type.dbValue.equals(dbValue))
				return type;
		return null;
	}

}

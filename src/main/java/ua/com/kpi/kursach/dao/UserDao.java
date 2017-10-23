package ua.com.kpi.kursach.dao;

import java.util.List;

import ua.com.kpi.kursach.dto.UserDTO;

public interface UserDao  {
	UserDTO getUserByLogin(String login);
	
	List<UserDTO> getAllUsers(); 
	
	List<UserDTO> getAllUsersByType(String Type); 
	
	void removeUserByLogin(String login);
	
	void updateUser(UserDTO user);
	
	void updateUserContactInfo(UserDTO user);
	
	void updateUserPassword(UserDTO user);
	
	String createUser(UserDTO user);
	
	boolean isUserExist(String login);
	

}

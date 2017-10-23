package ua.com.kpi.kursach.web.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.com.kpi.kursach.dao.UserDao;
import ua.com.kpi.kursach.dto.UserType;


@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("login", "error", error); 
	}
	
	@RequestMapping("/loginSucesss")
	public String redirectAfterSuccessLogin(Authentication auth) {
		String userType = userDao.getUserByLogin(auth.getName()).getType();
		String convertedType = UserType.getTypeByDBValue(userType).toString().toLowerCase();
		return "redirect:" + convertedType +"/main";
	}

}


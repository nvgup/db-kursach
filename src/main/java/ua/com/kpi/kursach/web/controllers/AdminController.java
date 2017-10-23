package ua.com.kpi.kursach.web.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.kpi.kursach.dao.FacultyGroupDao;
import ua.com.kpi.kursach.dao.UserDao;
import ua.com.kpi.kursach.dto.UserDTO;

@Controller
@RequestMapping(value = "/admin")
@Secured(value="ROLE_ADMIN")
public class AdminController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FacultyGroupDao facultyGroupDao;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getAllUsers(@RequestParam(required = false) String userType, Authentication auth,
			Map<String, Object> model) {
		model.put("currentUser", userDao.getUserByLogin(auth.getName()));
		model.put("usersList", getUsersByType(userType));
		model.put("userType", userType);
		return "admin/usersList";
	}

	@RequestMapping(value = "/user/remove/{login}", method = RequestMethod.GET)
	public String removeUserByLogin(@PathVariable String login) {
		userDao.removeUserByLogin(login);
		return "redirect:/admin/main";
	}

	@RequestMapping(value = "/user/edit/{login}", method = RequestMethod.GET)
	public String userDetail(@PathVariable String login, Map<String, Object> model) {
		model.put("user", userDao.getUserByLogin(login));
		model.put("groups", facultyGroupDao.getAllGroups());
		return "/admin/editUser";
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String updateUser(final @ModelAttribute("user") UserDTO user) {
		userDao.updateUser(user);
		return "redirect:/admin/main";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addUserPage(Map<String, Object> model) {
		model.put("user", new UserDTO());
		model.put("groups", facultyGroupDao.getAllGroups());
		return "/admin/addUser";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String createUser(final @ModelAttribute("user") UserDTO user, Map<String, Object> model) {
		if (userDao.isUserExist(user.getLogin())) {
			model.put("user", user);
			model.put("groups", facultyGroupDao.getAllGroups());
			model.put("error", "Користувач з даним логіном вже існує. Виберіть інший логін.");
			return "/admin/addUser";
		} else {
			userDao.createUser(user);
			return "redirect:/admin/main";
		}
	}

	private List<UserDTO> getUsersByType(String type) {
		return StringUtils.isEmpty(type) ? userDao.getAllUsers() : userDao.getAllUsersByType(type);
	}

}

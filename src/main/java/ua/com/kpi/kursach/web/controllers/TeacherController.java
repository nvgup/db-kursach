package ua.com.kpi.kursach.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.kpi.kursach.dao.CriteriaDao;
import ua.com.kpi.kursach.dao.DiplomDao;
import ua.com.kpi.kursach.dao.FacultyGroupDao;
import ua.com.kpi.kursach.dao.UserDao;
import ua.com.kpi.kursach.dto.CriteriaMarkDTO;

@Controller
@RequestMapping(value = "/teacher")
@Secured(value="ROLE_TEACHER")
public class TeacherController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FacultyGroupDao facultyGroupDao;
	@Autowired
	private DiplomDao diplomDao;
	@Autowired
	private CriteriaDao criteriaDao;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainPage(@RequestParam(required = false) String facultyGroup, Authentication auth,
			Map<String, Object> model) {
		model.put("currentUser", userDao.getUserByLogin(auth.getName()));
		model.put("groups", facultyGroupDao.getAllGroups());
		if (StringUtils.isEmpty(facultyGroup))
			model.put("diploms", diplomDao.getAllDiploms());
		else {
			model.put("diploms", diplomDao.getDiplomsByFaculty(facultyGroup));
			model.put("selectedGroup", facultyGroup);
		}
		return "teacher/main";
	}

	@RequestMapping(value = "/diplom/{id}", method = RequestMethod.GET)
	public String diplomPage(@PathVariable String id, Map<String, Object> model, Authentication auth) {
		List<Integer> allMarks = diplomDao.getTeacherDiplomMarks(id, auth.getName()).stream().map(mark->mark.getPoints()).collect(Collectors.toList());
		
		if (allMarks.isEmpty())		
			model.put("criterias", criteriaDao.getAllCriterias());
		else
			model.put("totalMark", allMarks.stream().reduce(0, Integer::sum));
		
		model.put("diplom", diplomDao.getDiplomByOwner(id));
		return "teacher/diplom";
	}

	@RequestMapping(value = "/markDiplom", method = RequestMethod.POST)
	public String mapDdiplom(HttpServletRequest req, Authentication auth) {
		List<CriteriaMarkDTO> criteriaMarks = new ArrayList<>();

		for (String criteria : criteriaDao.getAllCriteriaTitles())
			criteriaMarks.add(new CriteriaMarkDTO(criteria, Integer.valueOf(req.getParameter(criteria))));

		String diplomId = req.getParameter("diplomId");

		diplomDao.putDiplomMarks(diplomId, auth.getName(), criteriaMarks);
		return "redirect:/teacher/diplom/" + diplomId;

	}

}

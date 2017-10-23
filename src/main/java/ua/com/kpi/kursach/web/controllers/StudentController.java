package ua.com.kpi.kursach.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ua.com.kpi.kursach.dao.DiplomDao;
import ua.com.kpi.kursach.dao.UserDao;
import ua.com.kpi.kursach.dto.CriteriaMarkDTO;
import ua.com.kpi.kursach.dto.DiplomDTO;
import ua.com.kpi.kursach.dto.UserDTO;
import ua.com.kpi.kursach.util.FileUploader;

@Controller
@RequestMapping(value = "/student")
@Secured(value="ROLE_STUDENT")
public class StudentController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private DiplomDao diplomDao;
	@Autowired
	private FileUploader fileUploader;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String loginPage(Authentication auth, Map<String, Object> model) {
		UserDTO currentUser = userDao.getUserByLogin(auth.getName());
		model.put("currentUser", currentUser);
		model.put("diplom", diplomDao.getDiplomByOwner(auth.getName()));
		
		return "student/main";
	}

	@RequestMapping(value = "/contactInfo/edit", method = RequestMethod.GET)
	public String contactInfoEditPage(Authentication auth, Map<String, Object> model) {
		UserDTO currentUser = userDao.getUserByLogin(auth.getName());
		model.put("currentUser", currentUser);

		return "student/contactInfoEdit";
	}

	@RequestMapping(value = "/changePasswordPage", method = RequestMethod.GET)
	public String passwordEditPage(Authentication auth, Map<String, Object> model) {
		UserDTO currentUser = userDao.getUserByLogin(auth.getName());
		model.put("currentUser", currentUser);

		return "student/changePassword";
	}

	@RequestMapping(value = "/details/update", method = RequestMethod.POST)
	public String updateDetails(final @ModelAttribute("currentUser") UserDTO user) {
		userDao.updateUserContactInfo(user);
		return "redirect:/student/main";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String passwordUpdate(final @ModelAttribute("currentUser") UserDTO user) {
		userDao.updateUserPassword(user);
		return "redirect:/student/main";
	}

	@RequestMapping(value = "/diplomInfoPage", method = RequestMethod.GET)
	public String diplomPage(Authentication auth, Map<String, Object> model) {
		Map<String, List<CriteriaMarkDTO>> diplomMarks = diplomDao.getDiplomMarks(auth.getName());

		if (!diplomMarks.isEmpty()) {
			model.put("marks", diplomMarks);

			Map<String, Integer> totalMarks = new HashMap<>();
			for (String teacher : diplomMarks.keySet())
				totalMarks.put(teacher, diplomMarks.get(teacher).stream()
						.map(criteriaMark -> criteriaMark.getPoints())
						.reduce(0, Integer::sum));
			
			model.put("totalMarks", totalMarks);
			model.put("averageMarks", totalMarks.values().stream().mapToInt(e->e).average().orElse(0));
		}

		model.put("diplom", diplomDao.getDiplomByOwner(auth.getName()));
		return "/student/diplomInfoPage";
	}

	@RequestMapping(value = "/diplomAddPage", method = RequestMethod.GET)
	public String diplomAddPage(Authentication auth) {
		if (diplomDao.getDiplomByOwner(auth.getName()) == null)
			return "/student/diplomAddPage";
		else
			return "redirect:/student/diplomInfoPage";
	}

	@RequestMapping(value = "/diplomCreate", method = RequestMethod.POST)
	public String createDiplom(Authentication auth, @RequestParam("name") String name,
			@RequestParam("file") MultipartFile file, Map<String, Object> model) {
		String uploadedFilePath = fileUploader.getUploadedFilePath(file);

		if (uploadedFilePath == null) {
			model.put("error", "Невдалося зберегти файл. Спробуйте загрузити пізніше");
			model.put("name", name);
			return "/student/diplomAddPage";
		} else {
			UserDTO owner = new UserDTO();
			owner.setLogin(auth.getName());

			DiplomDTO diplom = new DiplomDTO();
			diplom.setFilePath(uploadedFilePath);
			diplom.setOwner(owner);
			diplom.setName(name);
			diplomDao.createDiplom(diplom);
			return "redirect:/student/diplomInfoPage";
		}
	}

}

package controller;

import java.util.List;

import org.springframework.cglib.core.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.CourseDatabaseRepository;
import service.impleption.StudentCSVImport;
import util.SystemConfig;

@Controller
public class InstrucAdminController implements util.Constants{
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";
	
	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID) throws CourseGroupFormationException, CopyCatMeDBConfigException
	{
		CourseDatabaseRepository courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		if (courseDB.isCurrentUserEnrolledAsRoleInCourse(INSTRUCTOR_ROLE) || courseDB.isCurrentUserEnrolledAsRoleInCourse(TA_ROLE))
		{
			return "course/instructoradmin";
		}
		else
		{
			return "logout";
		}
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin(
			Model model,
			@RequestParam(name = ID) long courseID,
			@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
			@RequestParam(name = FAILURES, required = false) List<String> failures,
			@RequestParam(name = DISPLAY_RESULTS) boolean displayResults) throws CourseGroupFormationException, CopyCatMeDBConfigException
	{
		CourseDatabaseRepository courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);
		if (courseDB.isCurrentUserEnrolledAsRoleInCourse(INSTRUCTOR_ROLE) || courseDB.isCurrentUserEnrolledAsRoleInCourse(TA_ROLE))
		{
			return "course/instructoradmin";
		}
		else
		{
			return "logout";
		}
	}

	
	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID) throws CourseGroupFormationException, CopyCatMeDBConfigException
	{
		CourseDatabaseRepository courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		if (courseDB.isCurrentUserEnrolledAsRoleInCourse(INSTRUCTOR_ROLE) || courseDB.isCurrentUserEnrolledAsRoleInCourse(TA_ROLE))
		{
			return "course/enrollta";
		}
		else
		{
			return "logout";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
   public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID) throws CourseGroupFormationException, CopyCatMeDBConfigException
   {
		CourseDatabaseRepository courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		course = courseDB.loadCourseByID(courseID, course);
		StudentCSVImport importer = new StudentCSVImport(course, file);
		ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
		mav.addObject("successful", importer.getSuccessResults());
		mav.addObject("failures", importer.getFailureResults());
		mav.addObject("displayresults", true);
		
		return mav;
   }
}

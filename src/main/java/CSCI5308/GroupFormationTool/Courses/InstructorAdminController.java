package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class InstructorAdminController
{
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";
	
	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		try {
			courseDB.loadCourseByID(courseID, course);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to load courses at this moment!!!");
			return "course/instructoradmin";
		}
		
		model.addAttribute("displayresults", false);
		try {
			if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
				 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
			{
				model.addAttribute("course", course);
				return "course/instructoradmin";
			}
			else
			{
				model.addAttribute("logout");
				return "login";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to load your courses at this moment!!!");
			return "course/instructoradmin";
		}
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin(
			Model model,
			@RequestParam(name = ID) long courseID,
			@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
			@RequestParam(name = FAILURES, required = false) List<String> failures,
			@RequestParam(name = DISPLAY_RESULTS) boolean displayResults)
	{
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		try {
			courseDB.loadCourseByID(courseID, course);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to load courses at this moment!!!");
			return "course/instructoradmin";
		}
		
		model.addAttribute("displayresults", false);
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);
		try {
			if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
				 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
			{
				model.addAttribute("course", course);
				return "course/instructoradmin";
			}
			else
			{
				model.addAttribute("logout");
				return "login";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to load your courses at this moment!!!");
			return "course/instructoradmin";
		}
	}

	
	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		try {
			courseDB.loadCourseByID(courseID, course);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to enroll TA at this moment!!!");
			return "course/enrollta";
		}
		model.addAttribute("course", course);
		try {
			if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
				 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
			{
				return "course/enrollta";
			}
			else
			{
				return "logout";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to enroll TA at this moment!!!");
			return "course/enrollta";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
   public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID)
   {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		try {
			courseDB.loadCourseByID(courseID, course);
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
			mav.addObject("errorMessage","Unable to upload CSV at this moment!!!");
			mav.addObject("displayresults", false);
			return mav;
		}
		IStudentCSVParser parser = new StudentCSVParser(file);
		StudentCSVImport importer = new StudentCSVImport(parser, course);
		ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
		mav.addObject("successful", importer.getSuccessResults());
		mav.addObject("failures", importer.getFailureResults());
		mav.addObject("displayresults", true);
		
		return mav;
   }
}

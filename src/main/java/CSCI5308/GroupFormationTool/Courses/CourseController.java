package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class CourseController
{
	private static final String ID = "id";
	
	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		try {
			courseDB.loadCourseByID(courseID, course);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to load this course at this moment");
			return "course/course";
		}
		
	
		List<Role> userRoles = null;
		try {
			userRoles = course.getAllRolesForCurrentUserInCourse();
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to load this course at this moment");
			return "course/course";
		}
		if (null == userRoles)
		{
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
		}
		else
		{
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
		}
		model.addAttribute("course", course);
		return "course/course";
	}
}

package CSCI5308.GroupFormationTool.Courses;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.UserAbstractFactory;

@Controller
public class CourseAdminController {
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String INSTRUCTOR = "instructor";

	@GetMapping("/admin/course")
	public String course(Model model) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		List<ICourse> allCourses = null;
		try {
			allCourses = courseDB.loadAllCourses();
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to get courses at this moment");
		}
		model.addAttribute("courses", allCourses);
		return "admin/course";
	}

	@GetMapping("/admin/assigninstructor")
	public String assignInstructor(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ICourse c = CourseAbstractFactory.getFactory().makeCourse();
		try {
			courseDB.loadCourseByID(courseID, c);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to assign instructor at this moment");
			return "admin/assigninstructor";
		}

		ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance()
				.getCourseUserRelationshipDB();
		List<IUser> allUsersNotCurrentlyInstructors;
		try {
			allUsersNotCurrentlyInstructors = courseUserRelationshipDB.findAllUsersWithoutCourseRole(Role.INSTRUCTOR,
					courseID);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to assign instructor at this moment");
			return "admin/assigninstructor";
		}
		model.addAttribute("course", c);
		model.addAttribute("users", allUsersNotCurrentlyInstructors);
		return "admin/assigninstructor";
	}

	@GetMapping("/admin/deletecourse")
	public ModelAndView deleteCourse(@RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ICourse c = CourseAbstractFactory.getFactory().makeCourse();
		c.setId(courseID);
		try {
			c.delete(courseDB);
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/admin/course");
			mav.addObject("errorMessage", "Unable to delete course at this moment");
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/admin/course");
		return mav;
	}

	@RequestMapping(value = "/admin/createcourse", method = RequestMethod.POST)
	public ModelAndView createCourse(@RequestParam(name = TITLE) String title) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ICourse c = CourseAbstractFactory.getFactory().makeCourse();
		c.setTitle(title);
		try {
			c.createCourse(courseDB);
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/admin/course");
			mav.addObject("errorMessage", "Unable to create course at this moment");
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/admin/course");
		return mav;
	}

	@RequestMapping(value = "/admin/assigninstructor", method = RequestMethod.POST)
	public ModelAndView assignInstructorToCourse(@RequestParam(name = INSTRUCTOR) List<Integer> instructor,
			@RequestParam(name = ID) long courseID) {
		ICourse c = CourseAbstractFactory.getFactory().makeCourse();
		c.setId(courseID);
		Iterator<Integer> iter = instructor.iterator();
		ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance()
				.getCourseUserRelationshipDB();
		while (iter.hasNext()) {
			IUser u = UserAbstractFactory.getFactory().makeUser();
			u.setId(iter.next().longValue());
			try {
				courseUserRelationshipDB.enrollUser(c, u, Role.INSTRUCTOR);
			} catch (Exception e) {
				ModelAndView mav = new ModelAndView("admin/course");
				mav.addObject("errorMessage", "Unable to assign instructor to the course at this moment");
				return mav;
			}
		}
		ModelAndView mav = new ModelAndView("redirect:/admin/course");
		return mav;
	}

}
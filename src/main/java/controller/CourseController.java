package controller;

import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.impl.CourseDatabaseRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
	private static final String courseID = "courseID";
	private static final String courseNAME = "courseN";

	@GetMapping("/courses")
	public String showCourses(Model model) {
		List<Course> courseList=new ArrayList<Course>();
		CourseDatabaseRepositoryImpl courseDB = new CourseDatabaseRepositoryImpl();
		try {
			courseList=courseDB.getAllCourses();
		} catch (CopyCatMeDBConfigException e) {
			e.printStackTrace();
		} catch (CourseGroupFormationException e) {
			e.printStackTrace();
		}
		model.addAttribute("courses",courseList);
		return "courses";
	}

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String viewCourse(@RequestParam(name = courseNAME) String courseName,Model model) {
		model.addAttribute("courseN",courseName);
		return "course";
	}

}

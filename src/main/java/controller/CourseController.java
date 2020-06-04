package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

	
	@GetMapping("/fetchAllCourses")
	public String fetchAllCourses() { 
		return "courses";
	}
}

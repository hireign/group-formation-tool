package controller;

import dao.AdminCourse;
import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.impl.CourseDatabaseRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminCourseController {
    private static final String courseID = "courseID";
    private static final String courseNAME = "courseName";

    @GetMapping("/admin")
    public String showAdminDashboard() { return "admin/dashboard"; }

    @GetMapping("/admin/courses")
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
        return "admin/courses";
    }

    @RequestMapping("/admin/deleteCourse")
    public ModelAndView deleteCourse(@RequestParam(name = courseID) String courseId, Model model){
        CourseDatabaseRepositoryImpl courseDB = new CourseDatabaseRepositoryImpl();
        String feedback;
        List<Course> courseList=new ArrayList<Course>();
        try {
            feedback = courseDB.deleteCourseById(courseId);
            courseList = courseDB.getAllCourses();
            model.addAttribute("feedback", feedback);
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }
        model.addAttribute("courses",courseList);
        ModelAndView newMAV = new ModelAndView("redirect:/admin/courses");
        return newMAV;
    }

    @RequestMapping(value = "/admin/addCourse", method = RequestMethod.POST)
    public ModelAndView addCourse(@RequestParam(name = courseID) String courseId,
                                  @RequestParam(name = courseNAME) String courseName, Model model){
        CourseDatabaseRepositoryImpl courseDB = new CourseDatabaseRepositoryImpl();
        String feedback;
        try {
            AdminCourse course2 = new AdminCourse();
            course2.setCourseId(courseId);
            course2.setCourseName(courseName);
            feedback = courseDB.addCourse(course2);
            model.addAttribute("feedback", feedback);
//            System.out.println("Printing for add course: "+courseId+" courseName: "+course2.getCourseName());
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }
        ModelAndView newMAV = new ModelAndView("redirect:/admin/courses");
        return newMAV;
    }

    @GetMapping("/admin/addCourse")
    public String addCourse() {
        return "admin/addCourse";
    }

    @GetMapping("/admin/addInstructor")
    public String addInstructor() { return "admin/addInstructor"; }

}

package controller;

import dao.AdminCourse;
import dao.CourseRole;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.impl.CourseDatabaseRepositoryImpl;
import repository.impl.CourseRoleDatabaseRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import static util.Constants.courseID;
import static util.Constants.userID;

@Controller
public class AdminInstructorController {
    @GetMapping("/admin/instructors")
    public String showInstructors(Model model) {
        List<CourseRole> instructorList=new ArrayList<CourseRole>();
        CourseRoleDatabaseRepositoryImpl courseRoleDB = new CourseRoleDatabaseRepositoryImpl();
        try {
            instructorList=courseRoleDB.getAllCourseRoleInstructor();
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }
        model.addAttribute("instructors",instructorList);
        return "admin/instructors";
    }
        @RequestMapping("/admin/deleteInstructor")
        public ModelAndView deleteInstructor(@RequestParam(name = userID) String instructorID, Model model) {
            CourseRoleDatabaseRepositoryImpl courseRoleDB = new CourseRoleDatabaseRepositoryImpl();
            String feedback;
            List<CourseRole> instructorList = new ArrayList<CourseRole>();
            try {
                feedback = courseRoleDB.deleteInstructorById(instructorID);
                instructorList = courseRoleDB.getAllCourseRoleInstructor();
                model.addAttribute("feedback", feedback);
            } catch (CopyCatMeDBConfigException e) {
                e.printStackTrace();
            } catch (CourseGroupFormationException e) {
                e.printStackTrace();
            }
            model.addAttribute("instructors",instructorList);
            ModelAndView newMAV = new ModelAndView("redirect:/admin/instructors");
            return newMAV;
        }

    @RequestMapping(value = "/admin/addInstructor", method = RequestMethod.POST)
    public ModelAndView addInstructor(@RequestParam(name = userID) String instructorId,
                                  @RequestParam(name = courseID) String courseId, Model model){
        CourseRoleDatabaseRepositoryImpl courseDB = new CourseRoleDatabaseRepositoryImpl();
        String feedback;
        try {
            CourseRole course2 = new CourseRole();
            course2.setCourseId(courseId);
            course2.setUserId(instructorId);
            feedback = courseDB.addInstructor(course2);
            model.addAttribute("feedback", feedback);
//            System.out.println("Printing for add course: "+courseId+" courseName: "+course2.getCourseName());
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }
        ModelAndView newMAV = new ModelAndView("redirect:/admin/instructors");
        return newMAV;
    }

}










package model;

import dao.AdminCourse;
import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.CourseDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

public class CourseDBMock implements CourseDatabaseRepository {
    private static final long courseid1 = 5000, courseid2 = 5100;
    public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException{
        List<Course> courseList = new ArrayList<Course>();
        Course course = new Course();
        course.setCourseId(courseid1);
        course.setCourseName("Course 1");
        courseList.add(course);
        Course course2 = new Course();
        course2.setCourseId(courseid2);
        course2.setCourseName("Course 2");
        courseList.add(course2);
        return courseList;
    }
    public Course loadCourseByID(long courseID, Course course) throws CourseGroupFormationException, CopyCatMeDBConfigException{
        course.setCourseId(courseID);
        course.setCourseName("course 1");
        return course;
    }
    public String deleteCourseById(String courseID) throws CopyCatMeDBConfigException, CourseGroupFormationException{
        AdminCourse course = new AdminCourse();
        course.setCourseId(courseID);
        course.setCourseName("course1");
        return courseID;
    }
    public String addCourse(AdminCourse course) throws CopyCatMeDBConfigException, CourseGroupFormationException{
        course.setCourseId("1234");
        course.setCourseName("course 1");
        return course.getCourseName();
    }
    public boolean isCurrentUserEnrolledAsRoleInCourse(int instructorRole){
        return true;
    }
}

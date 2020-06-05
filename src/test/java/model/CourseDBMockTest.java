package model;

import dao.AdminCourse;
import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseDBMockTest {

    private static final long courseId = 5000;
    private static final String courseID = "5000";
    private static final String courseName = "Course 1";

    @Test
    public void getAllCourses() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseDBMock courseDBMock = new CourseDBMock();
        List<Course> courseList = new ArrayList<>();
        try {
            courseList = courseDBMock.getAllCourses();
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }
        Assert.isTrue(courseList.size()>0);
    }

    @Test
    public void loadCourseByID() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseDBMock courseDBMock = new CourseDBMock();
        Course course = new Course();
        try {
            course = courseDBMock.loadCourseByID(courseId, course);
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }
        Assert.isTrue(course.getCourseId()==courseId);
    }

    @Test
    public void deleteCourseById() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseDBMock courseDBMock = new CourseDBMock();
        String string = courseDBMock.deleteCourseById(courseID);
        Assert.isTrue(string!=null);
    }

    @Test
    public void addCourse() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseDBMock courseDBMock = new CourseDBMock();
        AdminCourse course = new AdminCourse();
        String string = courseDBMock.addCourse(course);
        Assert.isTrue(string!=null);
    }
}

package model;

import dao.AdminCourse;
import dao.Course;
import dao.CourseRole;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRoleDBMockTest {

    @Test
    void enrollUserInCourse() {
    }

    @Test
    void getAllCourseRoleInstructor() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseRoleDBMock courseRoleDBMock = new CourseRoleDBMock();
        List<CourseRole> courseRoleList = new ArrayList<>();
        courseRoleList = courseRoleDBMock.getAllCourseRoleInstructor();
        Assert.isTrue(courseRoleList.size()>0);
    }

    @Test
    void deleteInstructorById() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseRoleDBMock courseRoleDBMock = new CourseRoleDBMock();
        String string = courseRoleDBMock.deleteInstructorById("7");
        Assert.isTrue(string!=null);
    }

    @Test
    void addInstructor() throws CourseGroupFormationException, CopyCatMeDBConfigException {
        CourseRoleDBMock courseRoleDBMock = new CourseRoleDBMock();
        CourseRole courseRole = new CourseRole();
        String string = courseRoleDBMock.addInstructor(courseRole);
        Assert.isTrue(string!=null);
    }
}
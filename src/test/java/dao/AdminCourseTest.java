package dao;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class AdminCourseTest {
    private static final String courseID = "5001";
    private static final String courseName = "courseName";

    @Test
    void getCourseId() {
        AdminCourse course = new AdminCourse();
        course.setCourseId(courseID);
        Assert.isTrue(courseID == course.getCourseId());
    }

    @Test
    void setCourseId() {
        AdminCourse course = new AdminCourse();
        course.setCourseId(courseID);
        Assert.isTrue(courseID == course.getCourseId());
    }

    @Test
    void getCourseName() {
        AdminCourse course = new AdminCourse();
        course.setCourseName(courseName);
        Assert.isTrue(courseName == course.getCourseName());
    }

    @Test
    void setCourseName() {
        AdminCourse course = new AdminCourse();
        course.setCourseName(courseName);
        Assert.isTrue(courseName == course.getCourseName());
    }
}
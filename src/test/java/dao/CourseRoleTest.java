package dao;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("deprecation")

class CourseRoleTest {
    private static final String courseId = "5100";
    private static final String roleId = "CourseRole";
    private static final String userId = "7";

    @Test
    void getCourseId() {
        CourseRole courseRole = new CourseRole();
        courseRole.setCourseId(courseId);
        Assert.isTrue(courseId == courseRole.getCourseId());
    }

    @Test
    void setCourseId() {
        CourseRole courseRole = new CourseRole();
        courseRole.setCourseId(courseId);
        Assert.isTrue(courseId == courseRole.getCourseId());
    }

    @Test
    void getRoleId() {
        CourseRole courseRole = new CourseRole();
        courseRole.setRoleId(roleId);
        Assert.isTrue(roleId.equals(courseRole.getRoleId()));
    }

    @Test
    void setRoleId() {
        CourseRole courseRole = new CourseRole();
        courseRole.setRoleId(roleId);
        Assert.isTrue(roleId.equals(courseRole.getRoleId()));
    }

    @Test
    void getUserId() {
        CourseRole courseRole = new CourseRole();
        courseRole.setUserId(userId);
        Assert.isTrue(userId == courseRole.getUserId());
    }

    @Test
    void setUserId() {
        CourseRole courseRole = new CourseRole();
        courseRole.setUserId(userId);
        Assert.isTrue(userId == courseRole.getUserId());
    }
}
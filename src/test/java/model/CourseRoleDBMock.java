package model;

import dao.Course;
import dao.CourseRole;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.CourseRoleDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

public class CourseRoleDBMock implements CourseRoleDatabaseRepository {
    public boolean enrollUserInCourse(int id, Long courseId, int roleId)
            throws CopyCatMeDBConfigException, CourseGroupFormationException{
        return true;
    }
    public List<CourseRole> getAllCourseRoleInstructor() throws CopyCatMeDBConfigException, CourseGroupFormationException{
        List<CourseRole> courseRoleList = new ArrayList<CourseRole>();
        CourseRole courseRole = new CourseRole();
        courseRole.setUserId("12");
        courseRole.setCourseId("5001");
        courseRole.setRoleId("3");
        courseRoleList.add(courseRole);
        CourseRole courseRole1 = new CourseRole();
        courseRole.setUserId("10");
        courseRole.setCourseId("5000");
        courseRole.setRoleId("4");
        courseRoleList.add(courseRole1);
        return courseRoleList;
    }
    public String deleteInstructorById(String userID) throws CopyCatMeDBConfigException, CourseGroupFormationException{
        CourseRole courseRole = new CourseRole();
        courseRole.setUserId(userID);
        courseRole.setCourseId("5000");
        courseRole.setRoleId("4");
        return "Following user: "+courseRole.getUserId()+" was deleted";
    }
    public String addInstructor(CourseRole instructor) throws CopyCatMeDBConfigException, CourseGroupFormationException{
        instructor.setRoleId("1234");
        instructor.setCourseId("5000");
        instructor.setUserId("3");
        return "Instructor "+instructor.getUserId()+"was added to database";
    }
}

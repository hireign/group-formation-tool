package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import config.DBConfig;
import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.CourseDatabaseRepository;
import util.Constants;
import util.LoggerUtil;

@Repository
public class CourseDatabaseRepositoryImpl implements CourseDatabaseRepository,Constants{

	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	
	@Override
	public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException {
		List<Course> courseList = new ArrayList<Course>();
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = selectCourses;
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			ResultSet rs = insertionps.executeQuery();
			while(rs.next()){
				Course course = new Course();
				course.setCourseId(rs.getLong(COURSEID));
				course.setCourseName(rs.getString(COURSENAME));
				courseList.add(course);
		}	
			
			}	 catch (SQLException e) {
				myLogger.info("An exception occurred while retrieving Courses from Database ", e);
				throw new CourseGroupFormationException("There was an error while fetching the list of courses.");
				} finally {
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) {
							myLogger.info("Failed to close database connection ", e);
						}
					}
				}
	
		return courseList;

	}

	@Override
	public Course fetchByCourseId(String courseId)
			throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		Course course = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT * from COURSES WHERE COURSEID = ?";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, courseId);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getLong(COURSEID));
				course.setCourseName(rs.getString(COURSENAME));
			}

		} catch (SQLException e) {
			myLogger.info("An exception occurred while fetching Course from Database ", e);
			throw new CourseGroupFormationException(String.format("There was an error while fetching the course record from database with course Id %s.",courseId));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}
		return course;

	}

	@Override
	public Course loadCourseByID(long courseID, Course course) throws CourseGroupFormationException, CopyCatMeDBConfigException {
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT * from Course WHERE id = ?";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setLong(1, courseID);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getLong(COURSEID));
				course.setCourseName(rs.getString(COURSENAME));
			}

		} catch (SQLException e) {
			myLogger.info("An exception occurred while fetching Course from Database ", e);
			throw new CourseGroupFormationException(String.format("There was an error while fetching the course record from database with course Id %s.",courseID));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}
		return course;
	}

	@Override
	public boolean isCurrentUserEnrolledAsRoleInCourse(int instructorRole) {
//		return userRoleDecider.userHasRoleInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), role, this);
		return false;
	}

}

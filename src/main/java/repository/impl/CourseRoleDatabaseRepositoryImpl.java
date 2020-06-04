package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AdminCourse;
import dao.CourseRole;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import config.DBConfig;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.CourseRoleDatabaseRepository;
import util.Constants;
import util.LoggerUtil;

@Repository
public class CourseRoleDatabaseRepositoryImpl implements CourseRoleDatabaseRepository,Constants {
	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	@Override
	public boolean enrollUserInCourse(int userID, Long courseId, int roleId)
			throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		boolean result = false;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			
				String sql = "INSERT INTO CourseRole(`courseID`,`roleID`,`userID`) VALUES (?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1, courseId);
				ps.setLong(2, roleId);
				ps.setLong(3, userID);
				ps.executeUpdate();
				ps.close();
				result = true;
			
		} catch (SQLException e) {
			myLogger.info("An exception occurred while retreving role of the User in Database ", e);
			throw new CourseGroupFormationException(String.format("There was an error while fetching role"));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}
		
		return result;
	}
	@Override
	public List<CourseRole> getAllCourseRoleInstructor() throws CopyCatMeDBConfigException, CourseGroupFormationException {
		List<CourseRole> courseRoleList = new ArrayList<CourseRole>();
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "select * from CourseRole";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			ResultSet rs = insertionps.executeQuery();
			while(rs.next()){
				CourseRole courseRole = new CourseRole();
				courseRole.setCourseId(rs.getString(courseID));
				courseRole.setRoleId(rs.getString(roleID));
				courseRole.setUserId(rs.getString(userID));
				courseRoleList.add(courseRole);
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
		return courseRoleList;
	}
	public String deleteInstructorById(String userID) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		int rows;
		String feedback;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "delete from CourseRole where userID = ?";
			PreparedStatement preparedStatement = con.prepareStatement(selectSql);
			preparedStatement.setString(1, userID);
			rows = preparedStatement.executeUpdate();
			if (rows == 1) {
				feedback = "1 row deleted successfully";
			} else {
				feedback = "Could not delete instructor: " + userID;
			}
		} catch (SQLException e) {
			feedback = "server error";
			myLogger.info("Failed to close database connection ", e);
		}
		return feedback;
	}
	@Override
	public String addInstructor(CourseRole instructor) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		int rows;
		String feedback;
		try{
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "insert into CourseRole values(?, ?, ?);";
			PreparedStatement insertion = con.prepareStatement(selectSql);
			insertion.setString(1, instructor.getCourseId());
			insertion.setString(2, "4");
			insertion.setString(3, instructor.getUserId());
			rows = insertion.executeUpdate();
			if(rows == 1){
				feedback = "1 row entered successfully";
			}
			else{
				feedback = "Error occured while adding instructor: "+instructor;
			}
		}
		catch (SQLException e){
			feedback = "server error";
			myLogger.info("Failed to close database connection ", e);
		}
		return feedback;
	}
}

package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

package dal.twentythree.gft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import dal.twentythree.gft.config.DBConfig;
import dal.twentythree.gft.dao.UserAccountStatus;
import dal.twentythree.gft.dao.UserContactInfo;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;
import dal.twentythree.gft.repository.UsersDatabaseRepository;
import dal.twentythree.gft.util.Constants;
import dal.twentythree.gft.util.LoggerUtil;

@Repository
public class UsersDatabaseRepositoryImpl implements UsersDatabaseRepository, Constants {

	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	
	@Override
	public Long createUser(UserContactInfo user) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		Long userID = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			//Check if a user already exists in the database
			String selectSql = "SELECT * from User WHERE bannerID = ?";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, user.getBannerId());
			ResultSet rs = insertionps.executeQuery();
			if (!rs.next()) {
				String sql = "INSERT INTO User (`bannerID`, `password`, `enabled`) VALUES (?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getBannerId());
				ps.setString(2, user.getPassword());
				ps.setInt(3, 0);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					userID = generatedKeys.getLong(1);
	                user.setId(userID);
	            }
				ps.close();
				
				String sqlContactInfo = "INSERT INTO UserContactInfo (userID, firstName, lastName, email) VALUES (?, ?, ?, ?)";
				PreparedStatement psCi = con.prepareStatement(sqlContactInfo);
				psCi.setLong(1, user.getId());
				psCi.setString(2, user.getFirstName());
				psCi.setString(3, user.getLastName());
				psCi.setString(4, user.getEmailId());
				
				psCi.executeUpdate();
				psCi.close();
				
				//-- Insert default role for new users
				String sqlRole = "INSERT INTO `SystemRole` (roleID, userID) VALUES (?, ?)";
				PreparedStatement psRole = con.prepareStatement(sqlRole);
				psRole.setInt(1, 2);	//-- GUEST role
				psRole.setLong(2, user.getId());
				psRole.executeUpdate();
				psRole.close();
				
				return userID;
			}
			else
			throw new CourseGroupFormationException(String.format("A user with banner Id  %s already exists.",user.getBannerId()));
		} catch (SQLException e) {
			myLogger.info("An exception occurred while inserting new User in Database ", e);
			throw new CourseGroupFormationException(String.format("There was an error while creating your account"));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}

	}

	@Override
	public List<UserContactInfo> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException {
		List<UserContactInfo> userList = new ArrayList<UserContactInfo>();
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = selectUsers;
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				UserContactInfo u = new UserContactInfo();
				u.setBannerId(rs.getString(BANNERID));
				u.setEmailId(rs.getString(EMAILID));
				u.setFirstName(rs.getString(FIRSTNAME));
				u.setLastName(rs.getString(LASTNAME));
				u.setId(rs.getLong(ID));
				userList.add(u);
			}

		} catch (SQLException e) {
			myLogger.info("An exception occurred while retrieving Users from Database ", e);
			throw new CourseGroupFormationException("There was an error while fetching the user records.");
			
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}

		return userList;
	}

	@Override
	public UserContactInfo fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		UserContactInfo u = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT * from User, UserContactInfo WHERE bannerID = ? and User.id = UserContactInfo.userID;";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, bannerId);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				u = new UserContactInfo();
				u.setEmailId(rs.getString(EMAILID));
				u.setFirstName(rs.getString(FIRSTNAME));
				u.setLastName(rs.getString(LASTNAME));
				u.setId(rs.getLong(ID));
			}

		} catch (SQLException e) {
			myLogger.info("An exception occurred while fetching User from Database ", e);
			throw new CourseGroupFormationException(String.format("There was an error while fetching the user records from database with banner Id %s.",bannerId));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}
		return u;
	}

	@Override
	public void saveAccountStatus(UserAccountStatus uas) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConfig.getDBConfigInstance().getConnectionInstance();
			String sql = "INSERT INTO `UserAccountStatus` (email, confirmationtoken) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uas.getEmail());
			ps.setString(2, uas.getConfirmationToken());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Failed to save account status");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public boolean verifyUser(String token, int i) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBConfig.getDBConfigInstance().getConnectionInstance();
			String sql = "SELECT * FROM `UserAccountStatus` WHERE confirmationtoken = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, token);
			rs = ps.executeQuery();
			if (rs.next()) {
				String email = rs.getString("email");
				
				//-- Delete confirmation token
				String sqlD = "DELETE FROM `UserAccountStatus` WHERE email = ?";
				ps = conn.prepareStatement(sqlD);
				ps.setString(1, email);
				ps.executeUpdate();
				
				//-- Enable user
				sqlD = "UPDATE `User` SET enabled = 1 WHERE id IN (SELECT userID from `UserContactInfo` WHERE email = ?)";
				ps = conn.prepareStatement(sqlD);
				ps.setString(1, email);
				ps.executeUpdate();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {}
		}
	}
}

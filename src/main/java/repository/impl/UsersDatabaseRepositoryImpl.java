package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import config.DBConfig;
import dao.User;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.UsersDatabaseRepository;
import util.Constants;
import util.IPasswordEncryption;
import util.LoggerUtil;

@Repository
public class UsersDatabaseRepositoryImpl implements UsersDatabaseRepository, Constants {

	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	String rawPassword;
	@Override
	public boolean createUser(User user, IPasswordEncryption PE, boolean SignUp) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			//Check if a user already exists in the database
			String selectSql = "SELECT * from User WHERE bannerID = ?";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, user.getBannerId());
			ResultSet rs = insertionps.executeQuery();
			if (!rs.next()) {
				if(null == user.getPassword())
					rawPassword = String.format("%s%s%s",user.getFirstName().substring(0,1),user.getBannerId().substring(2,6), "Rahul");
				else
					rawPassword = user.getPassword();
				
				String encryptedPassword = PE.encryptPassword(rawPassword);
				
				String sql = "INSERT INTO User (`bannerID`, `password`) VALUES (?, ?)";
				PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getBannerId());
				ps.setString(2, encryptedPassword);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
	                user.setId((generatedKeys.getInt(1)));
	            }
				ps.close();
				
				String sqlContactInfo = "INSERT INTO UserContactInfo (userID, firstName, lastName, email) VALUES (?, ?,?,?)";
				PreparedStatement psCi = con.prepareStatement(sqlContactInfo);
				psCi.setLong(1, user.getId());
				psCi.setString(2, user.getFirstName());
				psCi.setString(3, user.getLastName());
				psCi.setString(4, user.getEmailId());
				
				psCi.executeUpdate();
				psCi.close();

				return true;
			}
			else {
				user.setId(rs.getInt("id"));
				
				if(SignUp)
					return true;
				else
					throw new CourseGroupFormationException(String.format("A user with banner Id  %s already exists.",user.getBannerId()));
			}
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
	public List<User> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException {
		List<User> userList = new ArrayList<User>();
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = selectUsers;
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setBannerId(rs.getString(BANNERID));
				u.setEmailId(rs.getString(EMAILID));
				u.setFirstName(rs.getString(FIRSTNAME));
				u.setLastName(rs.getString(LASTNAME));
				u.setId(rs.getInt(ID));
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
	public User fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		User u = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT * from User, UserContactInfo WHERE bannerID = ? and User.id = UserContactInfo.userID;";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, bannerId);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setEmailId(rs.getString(EMAILID));
				u.setFirstName(rs.getString(FIRSTNAME));
				u.setLastName(rs.getString(LASTNAME));
				u.setId(rs.getInt(ID));
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

}
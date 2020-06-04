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
import dao.UserAccountStatus;
import dao.UserLogin;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.UsersDatabaseRepository;
import util.BCryptPasswordEncryption;
import util.Constants;
import util.IPasswordEncryption;
import util.LoggerUtil;

@Repository
public class UsersDatabaseRepositoryImpl implements UsersDatabaseRepository, Constants {
	IPasswordEncryption passEncryption = new BCryptPasswordEncryption();
	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	String rawPassword;

	@Override
	public boolean createUser(User user, IPasswordEncryption PE, boolean SignUp)
			throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			// Check if a user already exists in the database
			String selectSql = "SELECT * from User WHERE bannerID = ?";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, user.getBannerId());
			ResultSet rs = insertionps.executeQuery();
			if (!rs.next()) {
				if (null == user.getPassword())
					rawPassword = String.format("%s%s%s", user.getFirstName().substring(0, 1),
							user.getBannerId().substring(2, 6), "Rahul");
				else
					rawPassword = user.getPassword();

				String encryptedPassword = PE.encryptPassword(rawPassword);

				String sql = "INSERT INTO User (`bannerID`, `password`, `enabled`) VALUES (?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getBannerId());
				ps.setString(2, encryptedPassword);
				ps.setInt(3, 0);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					user.setId((generatedKeys.getInt(1)));
				}
				ps.close();

				String sqlRole = "INSERT INTO SystemRole (roleID, userID) VALUES (?, ?)";
				PreparedStatement psRole = con.prepareStatement(sqlRole);
				psRole.setInt(1, 2);
				psRole.setLong(2, user.getId());

				psRole.executeUpdate();
				psRole.close();
				
				String sqlContactInfo = "INSERT INTO UserContactInfo (userID, firstName, lastName, email) VALUES (?, ?,?,?)";
				PreparedStatement psCi = con.prepareStatement(sqlContactInfo);
				psCi.setLong(1, user.getId());
				psCi.setString(2, user.getFirstName());
				psCi.setString(3, user.getLastName());
				psCi.setString(4, user.getEmailId());

				psCi.executeUpdate();
				psCi.close();

				return true;
			} else {
				user.setId(rs.getInt("id"));

				if (SignUp)
					return true;
				else
					throw new CourseGroupFormationException(
							String.format("A user with banner Id  %s already exists.", user.getBannerId()));
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
			throw new CourseGroupFormationException(String.format(
					"There was an error while fetching the user records from database with banner Id %s.", bannerId));
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
	public boolean getUserLogin(UserLogin userLogin) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT * from `User` WHERE bannerID = ? and enabled = 1";
			ps = con.prepareStatement(selectSql);
			ps.setString(1, userLogin.getBannerId());
			rs = ps.executeQuery();
			if (rs.next()) {
				if (passEncryption.matches(userLogin.getPassword(), rs.getString("password"))) {
					return true;
				}
			}

			return false;
		} catch (Exception e) {
			myLogger.info("An exception occurred while fetching user from database ", e);
			throw new RuntimeException("An exception occurred while fetching user from database ");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception err) {
				myLogger.info("An exception occurred while closing the connection in getUserLogin() ", err);
			}
		}
	}

	@Override
	public String getUserRole(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT User.bannerID as username, Role.role as role FROM User JOIN SystemRole ON User.id=SystemRole.userID JOIN Role ON SystemRole.roleID=Role.id WHERE User.bannerID = ?";
			ps = con.prepareStatement(selectSql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("role");
			}

			return "GUEST";
		} catch (Exception e) {
			myLogger.info("An exception occurred while fetching roles for the user ", e);
			throw new RuntimeException("An exception occurred while fetching roles for the user.");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception err) {
				myLogger.info("An exception occurred while closing the connection in getUserRights() ", err);
			}
		}
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
			} catch (Exception e) {
			}
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

				// -- Delete confirmation token
				String sqlD = "DELETE FROM `UserAccountStatus` WHERE email = ?";
				ps = conn.prepareStatement(sqlD);
				ps.setString(1, email);
				ps.executeUpdate();

				// -- Enable user
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
			} catch (Exception e) {
			}
		}
	}
}

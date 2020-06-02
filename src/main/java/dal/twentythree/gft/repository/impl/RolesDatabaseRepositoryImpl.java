package dal.twentythree.gft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import dal.twentythree.gft.config.DBConfig;
import dal.twentythree.gft.dao.Role;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;
import dal.twentythree.gft.repository.RolesDatabaseRepository;
import dal.twentythree.gft.util.Constants;
import dal.twentythree.gft.util.LoggerUtil;

@Repository
public class RolesDatabaseRepositoryImpl implements RolesDatabaseRepository, Constants {

	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());

	@Override
	public Role fetchByRoleId(String roleId)
			throws CopyCatMeDBConfigException, CourseGroupFormationException {
		Connection con = null;
		Role role = null;
		try {
			con = DBConfig.getDBConfigInstance().getConnectionInstance();
			String selectSql = "SELECT * from Role WHERE id = ?";
			PreparedStatement insertionps = con.prepareStatement(selectSql);
			insertionps.setString(1, roleId);
			ResultSet rs = insertionps.executeQuery();
			while (rs.next()) {
				role = new Role();
				role.setRoleId(rs.getString(ROLEID));
				role.setRoleName(rs.getString(ROLEENAME));
			}

		} catch (SQLException e) {
			myLogger.info("An exception occurred while fetching Role from Database ", e);
			throw new CourseGroupFormationException(String.format(
					"There was an error while fetching the role record from database with role Id %s.", roleId));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					myLogger.info("Failed to close database connection ", e);
				}
			}
		}
		return role;

	}

}

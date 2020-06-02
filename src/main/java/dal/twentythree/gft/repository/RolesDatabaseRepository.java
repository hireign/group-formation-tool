package dal.twentythree.gft.repository;

import dal.twentythree.gft.dao.Role;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;

public interface RolesDatabaseRepository {
	
	public Role fetchByRoleId(String roleId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
}

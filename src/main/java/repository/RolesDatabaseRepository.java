package repository;

import dao.Role;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;

public interface RolesDatabaseRepository {
	
	public Role fetchByRoleId(String roleId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
}

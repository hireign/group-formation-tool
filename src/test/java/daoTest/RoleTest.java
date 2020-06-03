package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dao.Role;

@SpringBootTest
public class RoleTest {
	
	@Test
	public void setRoleIdTest() {
		Role role = new Role();
		role.setRoleId("ROLE001");
		assertEquals("ROLE001", role.getRoleId());
	}
	
	@Test
	public void getRoleIdTest() {
		Role role = new Role();
		role.setRoleId("ROLE001");
		assertEquals("ROLE001", role.getRoleId());
	}
	
	@Test
	public void setRoleNameTest() {
		Role role = new Role();
		role.setRoleName("ADMIN");
		assertEquals("ADMIN", role.getRoleName());
	}	
	
	@Test
	public void getRoleNameTest() {
		Role role = new Role();
		role.setRoleName("ADMIN");
		assertEquals("ADMIN", role.getRoleName());
	}	
	
}

package dal.twentythree.gft.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/", "/index", "/login", "/signup").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index").failureUrl("/login?error")
				.and().logout().permitAll();
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String sqlUsers = "SELECT bannerID as username, password, enabled FROM user WHERE bannerID = ?";
		String sqlAuth = "SELECT user.bannerID as username, role.role as role FROM user JOIN systemrole ON user.id=systemrole.userID JOIN role ON systemrole.roleID=role.id WHERE user.bannerID = ?";
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(sqlUsers)
				.authoritiesByUsernameQuery(sqlAuth);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

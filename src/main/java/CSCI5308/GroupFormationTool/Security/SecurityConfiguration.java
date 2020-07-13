package CSCI5308.GroupFormationTool.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;  

@Configuration  
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
   public void configure(WebSecurity web) throws Exception
	{
   	web.ignoring().antMatchers("/resources/**");
   }
   
	@Override  
	public void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/public/**", "/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.permitAll()
			.and().logout()
				.permitAll();
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception
	{
		return new CustomAuthenticationManager();
	}
}

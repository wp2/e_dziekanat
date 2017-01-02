package zut.wi.edziekanat;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{		
		http.authorizeRequests().antMatchers("/Login/").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/Login/")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		// TODO Auto-generated method stub
		auth.jdbcAuthentication();
		
	}

}

package zut.wi.edziekanat;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	// Handler do udanego logowania (głównie aby poprawnie przekierować żądanie)
	@Autowired
	LoginSuccessHandler loginSuccessHandler;
	
	// Handler do Nieudanego logowania
	@Autowired
	LoginFailureHandler loginFailureHandler;
	
	// Własny Provider do autoryzacji 
	@Autowired
	DziekanatSecurityProvider customSecurityProvider;
	
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{		
		auth.authenticationProvider(this.customSecurityProvider);				
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{		
		http.authorizeRequests().antMatchers("/Login/").permitAll()		
		.and()
		.formLogin()
		.loginPage("/Login/")
		.successHandler(this.loginSuccessHandler)	
		.failureForwardUrl("/error")
		.permitAll()		
		.and()		
		.logout()
		.permitAll()
		;
		http.csrf().disable()
		.authenticationProvider(this.customSecurityProvider);
	}
	
	
}

package zut.wi.edziekanat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import zut.wi.edziekanat.services.StudentService;

@Component
public class DziekanatSecurityProvider implements AuthenticationProvider 
{
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	StudentService studentService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException 
	{
		
		String [] loginType = httpRequest.getParameterValues("LoginType"); // Typ Logowania
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority("ROLE_STUDENT")); 
		String Login = auth.getName();
		String Passwd = auth.getCredentials().toString();
		return new UsernamePasswordAuthenticationToken("Hello","Passwd",grantedAuthority);	
		/*if( loginType[0] != null && loginType[0].equals("Student") )
		{
			String Login = auth.getName();
			String Passwd = auth.getCredentials().toString();
			if(studentService.getUserNamePassword(Login).equals(Passwd))
			{
				grantedAuthority.add(new SimpleGrantedAuthority("ROLE_STUDENT")); // Uprawnienia Studenta
				// TO DO Hook up service DB Pass AUTH 
				return new UsernamePasswordAuthenticationToken(Login,Passwd,grantedAuthority);		
			}
			else
			{
				return null;
			}
						
		}
		else if(loginType[0].equals("Dydaktyk"))
		{
			return new UsernamePasswordAuthenticationToken("C", "d",grantedAuthority);
		}
		else
		{
			return null;
		}*/
		
		
	}

	@Override
	public boolean supports(Class<?> auth) 
	{
		// TODO Auto-generated method stub
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}

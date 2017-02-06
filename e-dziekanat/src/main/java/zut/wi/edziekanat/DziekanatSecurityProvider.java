package zut.wi.edziekanat;

import java.nio.file.AccessDeniedException;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.management.BadAttributeValueExpException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javassist.tools.web.BadHttpRequest;
import zut.wi.edziekanat.entity.Dydaktyk;
import zut.wi.edziekanat.entity.Student;
import zut.wi.edziekanat.exceptions.StudentNotFound;
import zut.wi.edziekanat.services.DydaktykService;
import zut.wi.edziekanat.services.StudentService;

@Component
public class DziekanatSecurityProvider implements AuthenticationProvider 
{
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	DydaktykService dydaktykService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException 
	{
		
		String [] loginType = httpRequest.getParameterValues("LoginType"); // Typ Logowania
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		
		if( loginType[0] != null && loginType[0].equals("Student") && httpRequest.getParameter("Login") != null && httpRequest.getParameter("Hasło") != null  )
		{
			String Login = httpRequest.getParameter("Login");
			String Passwd = httpRequest.getParameter("Hasło");
			Student student = null;
			try 
			{
				student = studentService.getStudentByAlbum(Login);
			} 
			catch (StudentNotFound e) 
			{				
				throw new BadCredentialsException("");
			}
			if(student == null) throw new BadCredentialsException("f");				
				
			if(student.getAlbum().equals(Login) && student.getHaslo().equals(Passwd))
			{
				grantedAuthority.add(new SimpleGrantedAuthority("ROLE_STUDENT")); // Uprawnienia Studenta				
				return new UsernamePasswordAuthenticationToken(Login,Passwd,grantedAuthority);		
			}
			else
			{
				throw new BadCredentialsException("Login of Password is incorrect");
			}
						
		}
		else if(loginType[0] != null && loginType[0].equals("Dydaktyk") && httpRequest.getParameter("Login") != null && httpRequest.getParameter("Hasło") != null)
		{
			String Login = httpRequest.getParameter("Login");
			String Passwd = httpRequest.getParameter("Hasło");
			Dydaktyk dydaktyk = dydaktykService.getDydaktykById(Integer.parseInt(Login));
			if(dydaktyk.getId() == Integer.parseInt(Login) && Passwd.equals(dydaktyk.getHaslo()))
			{
				grantedAuthority.add(new SimpleGrantedAuthority("ROLE_DYDAKTYK"));
				return new UsernamePasswordAuthenticationToken(Login, Passwd,grantedAuthority);
			}
			else throw new BadCredentialsException("Login of Password is incorrect");
			
		}
		else
		{
			throw new BadCredentialsException("Incorrect account type selected");
		}
		
		
	}

	@Override
	public boolean supports(Class<?> auth) 
	{
		// TODO Auto-generated method stub
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}

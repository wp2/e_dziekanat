package zut.wi.edziekanat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler
{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Authentication auth)
			throws IOException, ServletException
	{
		handle(httpRequest,httpResponse,auth);
		
	}
	
	public void handle(HttpServletRequest httpRequest,HttpServletResponse httpResponse, Authentication auth)
	{
		String StudentURL="/Student/";
		String DydaktykURL = "/Dydaktyk/";
		try 
		{
			SimpleGrantedAuthority simple = new SimpleGrantedAuthority("ROLE_STUDENT");
			SimpleGrantedAuthority simple2 = new SimpleGrantedAuthority("ROLE_DYDAKTYK");
			if(auth.getAuthorities().contains(simple))
			{
				redirectStrategy.sendRedirect(httpRequest, httpResponse, StudentURL);
			}
			if(auth.getAuthorities().contains(simple2))
			{
				redirectStrategy.sendRedirect(httpRequest, httpResponse, DydaktykURL);
			}
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		}
				
	}

}

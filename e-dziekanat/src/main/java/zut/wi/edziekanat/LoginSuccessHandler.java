package zut.wi.edziekanat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
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
			redirectStrategy.sendRedirect(httpRequest, httpResponse, StudentURL);
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		}
				
	}

}

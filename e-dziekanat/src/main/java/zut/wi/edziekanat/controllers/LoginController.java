package zut.wi.edziekanat.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/","/Login"})
public class LoginController 
{
	@GetMapping(value="/")	
	public String Login()
	{
		return "Login/Login";
		
	}
	
	

}

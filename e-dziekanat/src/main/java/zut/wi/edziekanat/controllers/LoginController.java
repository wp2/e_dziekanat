package zut.wi.edziekanat.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController 
{
	@GetMapping(value="/")	
	public String Login()
	{
		return "Login/Login";
	}
	
	

}

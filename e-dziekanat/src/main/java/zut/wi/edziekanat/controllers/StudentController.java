package zut.wi.edziekanat.controllers;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Student")
public class StudentController {
	
	@GetMapping(value="/")	
	public String Hello()
	{
		return "Home";
	}

}

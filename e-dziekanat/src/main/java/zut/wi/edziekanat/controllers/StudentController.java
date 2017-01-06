package zut.wi.edziekanat.controllers;



import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Student")
public class StudentController {
	
	@GetMapping(value="/")	
	//@PreAuthorize("hasRole('ROLE_DYDAKTYK') && isAuthenticated()")
	@Secured("ROLE_STUDENT")
	public String Home()
	{
		
		return "Student/StudentOgloszenia";
	}
	
	@GetMapping(value="/DaneStudenta")
	@Secured("ROLE_STUDENT")
	public String DaneStudenta(Principal principal)
	{
		// Pobierz informacje o studencie wykorzystujemy login podany przy autoryzacji z obiektu Principal
		return "Student/DaneStudenta";
	}

}

package zut.wi.edziekanat.controllers;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zut.wi.edziekanat.entity.Student;
import zut.wi.edziekanat.services.StudentService;

@Controller
@RequestMapping("/Student")
public class StudentController
{
	@Autowired
	StudentService studentService;
	
	@GetMapping(value="/")	
	//@PreAuthorize("hasRole('ROLE_DYDAKTYK') && isAuthenticated()")
	@Secured("ROLE_STUDENT")
	public String Home()
	{
		
		return "Student/StudentOgloszenia";
	}
	
	@GetMapping(value="/DaneStudenta")
	@Secured("ROLE_STUDENT")
	public String DaneStudenta(Principal principal,Model model)
	{
		model.addAttribute("student", studentService.getStudentDataByAlbum(principal.getName()));
		// Pobierz informacje o studencie wykorzystujemy login podany przy autoryzacji z obiektu Principal		
		return "Student/DaneStudenta";
	}
	
	@GetMapping(value="/SemestrInformacje")
	@Secured("ROLE_STUDENT")
	public String semestrInfoStudenta(Principal principal,Model model)
	{
		model.addAttribute("student", studentService.getStudentSemestrAndGroupsInfo(principal.getName()));
		// Pobierz informacje o studencie wykorzystujemy login podany przy autoryzacji z obiektu Principal		
		return "Student/SemestrInformacje";
	}
	
	@GetMapping(value="/Przedmioty")
	@Secured("ROLE_STUDENT")
	public String studentKursy(Principal principal,Model model)
	{
		model.addAttribute("student", studentService.getStudentSemestrAndGroupsInfo(principal.getName()));
		// Pobierz informacje o studencie wykorzystujemy login podany przy autoryzacji z obiektu Principal		
		return "Student/Przedmioty";
	}

}

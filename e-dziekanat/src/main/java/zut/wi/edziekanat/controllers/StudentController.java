package zut.wi.edziekanat.controllers;



import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import zut.wi.edziekanat.entity.Email;
import zut.wi.edziekanat.entity.KursyStudenta;
import zut.wi.edziekanat.entity.Student;
import zut.wi.edziekanat.services.SMTP;
import zut.wi.edziekanat.services.StudentService;

@Controller
@RequestMapping("/Student")
public class StudentController
{
	@Autowired
	StudentService studentService;
	
	@GetMapping(value="/")	
	@ResponseStatus(code=HttpStatus.OK)
	//@PreAuthorize("hasRole('ROLE_DYDAKTYK') && isAuthenticated()")
	@Secured("ROLE_STUDENT")
	public String Home()
	{
		
		return "Student/StudentOgloszenia";
	}
	
	@GetMapping(value="/DaneStudenta")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String DaneStudenta(Principal principal,Model model)
	{
		model.addAttribute("student", studentService.getStudentDataByAlbum(principal.getName()));
		// Pobierz informacje o studencie wykorzystujemy login podany przy autoryzacji z obiektu Principal		
		return "Student/DaneStudenta";
	}
	
	@GetMapping(value="/SemestrInformacje")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String semestrInfoStudenta(Principal principal,Model model)
	{
		model.addAttribute("student", studentService.getStudentSemestrAndGroupsInfo(principal.getName()));
		// Pobierz informacje o studencie wykorzystujemy login podany przy autoryzacji z obiektu Principal		
		return "Student/SemestrInformacje";
	}
	
	@GetMapping(value="/Przedmioty")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String studentKursy(Principal principal,Model model)
	{
		List<KursyStudenta> kursy;
		model.addAttribute("ListaKursow",studentService.getStudentKursy(principal.getName()) );
		
		return "Student/Przedmioty";
	}
	
	@GetMapping(value="/KontaktzDziekanatem")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String kontaktzDziekanatem(Principal principal,Email email)	
	{		
		return "Student/DziekanatEmail";
	}
	
	// Metoda POST do wysłania emailu poprzez AJAX
	@PostMapping(value="/KontaktzDziekanatem")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String wyslijEmail(Principal principal,Email email)
	{
		// wyślij email
		SMTP smtp = new SMTP();
		smtp.PrepareMessage("wpardel@gmail.com", email.topic, email.msgText);	
		return "Student/DziekanatEmail";
	}

}

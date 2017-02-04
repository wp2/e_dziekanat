package zut.wi.edziekanat.controllers;



import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zut.wi.edziekanat.entity.Email;
import zut.wi.edziekanat.entity.KursyStudenta;

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
	public String studentKursy(Principal principal,Model model,@RequestParam(value="SemestrNum",required=false,defaultValue="0")Integer SemestrNum)
	{
		if(SemestrNum == 0)
		{
			System.out.println("Current Semestr");
		}
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
	@PostMapping(value="/KontaktzDziekanatem",consumes="application/json")	
	@Secured("ROLE_STUDENT")	
	public @ResponseBody String wyslijEmail(@RequestBody Email email)
	{	
		// TODO Send Email		
		return "250 OK";
	}
	
	@GetMapping(value="/PracaDyplomowa")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String pracaDyplomowa(Principal principal,Model model)	
	{
		model.addAttribute("praca",this.studentService.getStudentPracaDyplomowa(principal.getName()));
		return "Student/DyplomowaInformacje";
	}
	
	@GetMapping(value="/Finanse")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String należnościStudenta(Principal principal,Model model)	
	{
		model.addAttribute("ListaOplat",this.studentService.getStudentOplaty(principal.getName()));
		return "Student/Finanse";
	}
	
	@GetMapping(value="/Oceny")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_STUDENT")
	public String ocenyStudenta(Principal principal,Model model)	
	{
		model.addAttribute("ListaKursow",this.studentService.getStudentOceny(principal.getName()));
		return "Student/StudentOceny";
	}


}

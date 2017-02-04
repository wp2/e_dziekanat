package zut.wi.edziekanat.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zut.wi.edziekanat.entity.Dydaktyk;
import zut.wi.edziekanat.entity.Email;
import zut.wi.edziekanat.entity.StudentOceny;
import zut.wi.edziekanat.services.DydaktykService;

@Controller
@RequestMapping("/Dydaktyk")
public class DydaktykController 
{
	
	@Autowired
	DydaktykService dydaktykService;
	
	@GetMapping(value="/")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String OgłoszeniaPracownika(Principal principal,Model model)
	{		
		return "Dydaktyk/PracownikOgłoszenia";
	}
	
	@GetMapping(value="/DanePracownika")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String DanePracownika(Principal principal,Model model)
	{
		model.addAttribute("dydaktyk", this.dydaktykService.getDydaktykById(Integer.parseInt(principal.getName())));
		return "Dydaktyk/PracownikDane";
	}

	@GetMapping(value="/Finanse")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String FinansePracownika(Principal principal,Model model)
	{
		model.addAttribute("zatrudnienie", this.dydaktykService.getDydaktykZatrudnienie(Integer.parseInt(principal.getName())));
		return "Dydaktyk/PracownikFinanse";
	}
	
	@GetMapping(value="/Przedmioty")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String PrzedmiotyPracownika(Principal principal,Model model)
	{
		model.addAttribute("ListaKursow", this.dydaktykService.getDydaktykPrzedmioty(Integer.parseInt(principal.getName())));
		return "Dydaktyk/PracownikPrzedmioty";
	}
	
	@GetMapping(value="/KontaktzDziekanatem")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String DziekanatKontakt(Principal principal,Email email)
	{		
		return "Dydaktyk/DziekanatEmail";
	}
	
	@PostMapping(value="/KontaktzDziekanatem",consumes="application/json")	
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")	
	public @ResponseBody String wyslijEmail(@RequestBody Email email)
	{	
		// TODO Send Email		
		return "250 OK";
	}
	
	@GetMapping(value="/PraceDyplomowe")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String PraceDyplomowePracownika(Principal principal,Model model)
	{		
		return "Dydaktyk/PracownikDyplomowe";
	}
	
	@GetMapping(value="/Oceny")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public String PracownikOcenianie(Principal principal,Model model,StudentOceny ocenaStudenta)
	{
		model.addAttribute("ListaPrzedmiotow",dydaktykService.getKursyOceny(Integer.parseInt(principal.getName())));
		return "Dydaktyk/PracownikOceny";
	}
	
	@PostMapping(value="/Oceny")
	@ResponseStatus(code=HttpStatus.OK)
	@Secured("ROLE_DYDAKTYK")
	public @ResponseBody String ZapiszOceneStudenta(StudentOceny ocena)
	{
		// AJAX CALL SAVE TO DB 
		return "200 OK";
	}

}

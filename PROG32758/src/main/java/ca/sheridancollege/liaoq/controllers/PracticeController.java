package ca.sheridancollege.liaoq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.liaoq.beans.Calculation;
import ca.sheridancollege.liaoq.database.DatabaseAccess;

@Controller
public class PracticeController {
	@Autowired
	private DatabaseAccess da;
		
//	@GetMapping("/")
//	public String getIndex(Model model) {	
//		return "index";
//	}
	
	@GetMapping("/list")
	public String getList(Calculation calculation, Model model) {
		model.addAttribute("calculations", da.getList1());
		return "list";
	}
	
	@GetMapping("/delete/{id}")
	public String getDelete(@PathVariable Integer id) {
		da.deleteCalc(id);
		return "redirect:/list";
	}
	
	
	@GetMapping("/calc")
	public String getCalc(Calculation calculation, Model model) {
		//model.addAttribute("calculations", da.getCalc());		
		return "calc";
	}
	
	@PostMapping("/calc")
	public String postCal(@ModelAttribute Calculation calculation ) {
		da.addCalc(calculation);
		return "redirect:/calc";
	}
	
	@GetMapping("/res")
	public String getRes(Calculation calculation, Model model) {//connect to databaseAccess
		model.addAttribute("cor",da.getCorrectRes());
		model.addAttribute("incor",da.getIncorrectRes());	
		return "res";
	}

	
}

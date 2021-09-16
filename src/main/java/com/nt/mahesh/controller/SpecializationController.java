package com.nt.mahesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.mahesh.entity.Specialization;
import com.nt.mahesh.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService service;
	
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization,Model model) {
		long id=service.saveSpecialization(specialization);
		String message="Record("+id+")inserted";
		model.addAttribute("message", message);
				return "SpecializationRegister";
	}
	@GetMapping("/all")
	public String viewAll(
			Model model,
			@RequestParam(value = "message",required = false) String message
			)
	{
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}

	@GetMapping("/delete")
	public String deleteData(
			@RequestParam Long id,
			RedirectAttributes attributes
			) 
	{
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Record ("+id+") is removed");
		return "redirect:all";
	}

}

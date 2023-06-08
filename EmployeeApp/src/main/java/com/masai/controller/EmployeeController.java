package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masai.model.Employee;
import com.masai.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
	
		return "index";
	}
	
	@GetMapping("/showNewEmployeeFrom")
	public String showNewEmployeeFrom(Model model) {
		
		Employee employee=new Employee();
		model.addAttribute("employee", employee);
	
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee( @ModelAttribute("employee") Employee employee ) {
		
		employeeService.saveEmployee(employee);
		
		return "redirect:/";
	}
	@GetMapping("/showFromForUpdate/{id}")
	public String showFromForUpdate(@PathVariable(value = "id") long id,Model model) {
		
		Employee employee=employeeService.getEmployeeById(id);
		
		model.addAttribute("employee", employee);
		
		return "Update_employee";
		
	}
	@GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method 
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}

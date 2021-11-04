package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@ModelAttribute
	UpdateEmployeeForm setUpEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	@Autowired
	private EmployeeService service;
	
	@RequestMapping("")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList", employeeList);
		System.out.println(employeeList.get(0).getDependentsCount());
		return "employee/list";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	@RequestMapping("/update")
	public String update(Integer id, Integer dependentsCount) {
		service.update(id, dependentsCount);
		return "redirect:/employee/showList";
	}
	
}

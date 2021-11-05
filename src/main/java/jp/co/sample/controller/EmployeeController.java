package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateAllEmployeeForm;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@ModelAttribute
	UpdateEmployeeForm setUpEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	@ModelAttribute
	UpdateAllEmployeeForm setUpAllEmployeeForm() {
		return new UpdateAllEmployeeForm();
	}
	
	@Autowired
	private EmployeeService service;
	
	/** 全従業員情報を取得 */
	@RequestMapping("/showList")
	public String showList(Integer page, Model model) {
		List<Employee> employeeList = service.showList(page);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("page", page);
		return "employee/list";
	}
	
	/** 従業員の詳細情報を表示 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model, Integer page) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		model.addAttribute("page", page);
		return "employee/detail";
	}
	
	@RequestMapping("/update")
	public String update(Integer id, Integer dependentsCount) {
		service.update(id, dependentsCount);
		return "redirect:/employee/showList";
	}
	
	@RequestMapping("/management")
	public String management(Integer page, Model model) {
		List<Employee> employeeList = service.showList(page);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("page", page);
		return "employee/management";
	}
	
	@RequestMapping("/toContent")
	public String toContent(Integer id, Model model) {
		Employee employee = service.showDetail(id);
		model.addAttribute("employee", employee);
		return "employee/content";
	}
	
	@RequestMapping("/updateAll")
	public String updateAll(UpdateAllEmployeeForm form) {
		System.out.println(form.getId());
		Employee employee = service.showDetail(form.getId());
		if(!form.getName().isEmpty()) {
			employee.setName(form.getName());
		}
		if(!form.getGender().isEmpty()) {
			employee.setGender(form.getGender());
		}
		if(!form.getMailAddress().isEmpty()) {
			employee.setMailAddress(form.getMailAddress());
		}
		if(!form.getZipCode().isEmpty()) {
			employee.setZipCode(form.getZipCode());
		}
		if(!form.getAddress().isEmpty()) {
			employee.setAddress(form.getAddress());
		}
		if(!form.getTelephone().isEmpty()) {
			employee.setTelephone(form.getTelephone());
		}
		if(!form.getSalary().isEmpty()) {
			employee.setSalary(Integer.parseInt(form.getSalary()));
		}
		if(!form.getDependentsCount().isEmpty()) {
			employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		}
		service.updateAll(employee);
		return "forward:/employee/showList?page=0";
	}
	
}

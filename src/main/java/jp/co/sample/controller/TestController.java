package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Controller
@RequestMapping("/sample")
public class TestController {
	
	@Autowired
	private EmployeeRepository employee;
	
	@RequestMapping("")
	public String index() {
		Employee emp = new Employee();
		employee.update(emp);
		return "hello";
	}

}

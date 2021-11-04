package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> showList() {
		return repository.findAll();
	}
	
	public Employee showDetail(Integer id) {
		Employee employee = repository.load(id);
		return employee;
	}
	
	public void update(Integer id, Integer dependentsCount) {
		Employee employee = repository.load(id);
		employee.setDependentsCount(dependentsCount);
		repository.update(employee);
	}

}

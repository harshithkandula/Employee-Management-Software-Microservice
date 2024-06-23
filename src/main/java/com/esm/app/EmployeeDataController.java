package com.esm.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esm.app.model.Employee;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/registry/employees")
public class EmployeeDataController {

	@Autowired
	private EmpRepo empRepo;
	
	@GetMapping("/all")
	public List<Employee> getAllEmployeesData(){
		
		List<Employee> empl = new ArrayList<Employee>();
		empl = empRepo.findAll();
		for(Employee e: empl) {
			System.out.println(e.getId()+" | "+e.getFirstName()+" | "+e.getEmailId());

		}
		   System.out.println("Welcome "+empl.size());
		   return empl;	
		}
	
	@PostMapping("/add-employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		 employee = empRepo.save(employee);
		 return employee;
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeDetails(@PathVariable long id) {
		Optional<Employee> emp = empRepo.findById(id);
		
		Employee e = emp.get();
		return e;
	}
	
	@PutMapping("/edit-employee/{id}")
	public Employee editEmployeeDetails(@PathVariable long id, @RequestBody Employee ed) {
		Optional<Employee> e = empRepo.findById(id);
		Employee emp = null;
		if(e.isPresent()) {
			emp = e.get();
		}
		
		emp.setFirstName(ed.getFirstName());
		emp.setLastName(ed.getLastName());
		emp.setPhNo(ed.getPhNo());
		emp.setRole(ed.getRole());
		emp.setSalary(ed.getSalary());
		emp.setWorkExperience(ed.getWorkExperience());
		emp.setJoiningDate(ed.getJoiningDate());
		
		empRepo.save(emp);
		
		return emp;
	}
	
	@DeleteMapping("/delete-employee/{id}")
	public void deleteEmployee(@PathVariable long id) {
		Employee emp = null;
		Optional<Employee> e = empRepo.findById(id);
		if(e.isPresent()) {
			 emp = e.get();
			 
		}
		
		empRepo.deleteById(emp.getId());
		//String res = "Deleted Employee "+emp.getFirstName()+" with ID "+emp.getId();
		//return res;
	}
}

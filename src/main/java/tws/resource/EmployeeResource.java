package tws.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tws.domain.Employee;
import tws.mapper.EmployeeMapper;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> queryList() {
		return employeeMapper.queryList();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> queryEmployeeById(@PathVariable Integer id) {
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		if (employeeMapper.queryEmployeeById(id) != null) {
			return ResponseEntity.ok(employeeMapper.queryEmployeeById(id));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
		if (employeeMapper.queryEmployeeById(employee.getId()) == null) {
			employeeMapper.insert(employee);
			return ResponseEntity.created(null).build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
		if (employeeMapper.queryEmployeeById(employee.getId()) != null) {
			employeeMapper.update(employee);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Integer	id) {
		if (id == null) {
			return  ResponseEntity.badRequest().build();
		}
		if (employeeMapper.queryEmployeeById(id) != null) {
			employeeMapper.deleteEmployeeById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
}

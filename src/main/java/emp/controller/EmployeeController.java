package emp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import emp.DAO.EmployeeDao;
import emp.entities.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	// To be refactored in order to handle form data
	@RequestMapping(value = "/Employee/add", method = RequestMethod.POST, consumes="application/json" )
	public void addEmployee(@RequestBody String json) {
		ObjectMapper om = new ObjectMapper();
		Employee employee = null;
		try {

			// Convert JSON string to Object
			employee = om.readValue(json, Employee.class);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		employeeDao.add(employee);

	}
	
	// To be refactored in order to handle form data
	@RequestMapping(value = "/Employee/put", method = RequestMethod.PUT)
	public void updateEmployee(@RequestBody String json) {
		ObjectMapper om = new ObjectMapper();
		Employee employee = null;
		try {

			// Convert JSON string to Object
			employee = om.readValue(json, Employee.class);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		employeeDao.update(employee);

	}

	@RequestMapping(value = "/Employees", method = RequestMethod.GET)
	public List<Employee> getEmployees() {
		return employeeDao.getAllEmployeesRowMapper();
	}
	
	@RequestMapping(value = "/Employee/get", method = RequestMethod.GET)
	public Employee getEmployeeById(@RequestParam("id") String id) {
		return employeeDao.getEmployeeById(Integer.valueOf(id));
	}

	@RequestMapping(value = "/Employee/delete", method = RequestMethod.DELETE)
	public void deleteEmployeeById(@RequestParam("id") String id){
		employeeDao.delete(Integer.valueOf(id));
	}

}

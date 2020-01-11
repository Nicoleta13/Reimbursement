package DAO;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	public Employee getEmployee(int em_id);
	public Employee getEmployee(String username, String password);
	public Employee getEmployee(String username);
	public List<Employee> getEmployees();
	public List<Employee> getSubordinates(int supervisor);

}

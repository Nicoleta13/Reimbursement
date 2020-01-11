package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.JDBCConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	public boolean addEmployee(Employee employee) {

		try {
			String sql = "CALL add_Employee(?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, employee.getSupervisor());
			cs.setInt(2, employee.getDep_id());
			cs.setString(3, employee.getUsername());
			cs.setString(4, employee.getPassword());
			cs.setString(5, employee.getFirst_name());
			cs.setString(6, employee.getLast_name());
			cs.setString(7, employee.getEmail());
			cs.setString(8, employee.getRole());
			
			cs.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Employee getEmployee(String username, String password) {
		
		try {
			String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, (username));
			ps.setString(2, (password));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				
				return (new Employee(
						rs.getInt("EM_ID"),
						rs.getInt("SUPERVISOR"),
						rs.getInt("DEP_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("EMAIL"),
						rs.getString("ROLE"),
						rs.getDouble("AV_FUNDS")));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee getEmployee(String username) {
		
		try {
			String sql = "SELECT * FROM employees WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, (username));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				
				return (new Employee(
						rs.getInt("EM_ID"),
						rs.getInt("SUPERVISOR"),
						rs.getInt("DEP_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("EMAIL"),
						rs.getString("ROLE"),
						rs.getDouble("AV_FUNDS")));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Employee getEmployee(int em_id) {
		
		try {
			String sql = "SELECT * FROM employees WHERE em_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(em_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				
				return (new Employee(
						rs.getInt("EM_ID"),
						rs.getInt("SUPERVISOR"),
						rs.getInt("DEP_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("EMAIL"),
						rs.getString("ROLE"),
						rs.getDouble("AV_FUNDS")));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Employee> getEmployees() {
		
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			String sql = "SELECT * FROM employees";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employees.add(new Employee(
						rs.getInt("EM_ID"),
						rs.getInt("SUPERVISOR"),
						rs.getInt("DEP_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("EMAIL"),
						rs.getString("ROLE"),
						rs.getDouble("AV_FUNDS")));
			}		
			return employees;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public List<Employee> getSubordinates(int supervisor){
		
		List<Employee> subordinates = new ArrayList<Employee>();
		
		try {
			String sql = "SELECT * FROM employees where supervisor = ?";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, supervisor);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				subordinates.add(new Employee(
						rs.getInt("EM_ID"),
						rs.getInt("SUPERVISOR"),
						rs.getInt("DEP_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("EMAIL"),
						rs.getString("ROLE"),
						rs.getDouble("AV_FUNDS")));
			}		
			return subordinates;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

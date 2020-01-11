package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import model.Employee;
import util.JDBCConnection;

public class EmployeeService {
	
	public static EmployeeDAO employList = new EmployeeDAOImpl();
	
	public static Connection conn = JDBCConnection.getConnection();
	
	public static int userId;
	
	public static boolean addEmployee(Employee employee) {
		return employList.addEmployee(employee);
	}
	public static Employee getEmployee(String username, String password) {
		return employList.getEmployee(username, password);
	}
	
	public static Employee getEmployee(String username) {
		return employList.getEmployee(username);
	}

	public static Employee getEmployee(int em_id) {	
		return employList.getEmployee(em_id);
	}

	public static List<Employee> getEmployees() {
		return employList.getEmployees();
	}
	
	public static List<Employee> getSubordinates(int supervisor){
		return employList.getSubordinates(supervisor);
	}
	
	public static boolean login(String username, String password) { 
	    
	    Employee getEmplLoged = getEmployee(username, password);
	    
	    if (getEmplLoged != null) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	public static double getAv_Funds(int em_id) {
		return getEmployee(em_id).getAv_funds();
	}
	
	
	public static boolean updateFundsNewYear(int em_id) {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int newYear = year + 1;
		cal.set(newYear, 0, 1, 00, 00, 00);
		Date newDate = cal.getTime();
		//System.out.println(newDate);
		
		double getCurrFunds = getAv_Funds(em_id);
		System.out.println(getCurrFunds);
		
		try {
			
			if(currentDate != newDate) {
				String sql = "UPDATE employees SET av_funds = 1000 WHERE em_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, Integer.toString(em_id));
				ps.executeQuery();
			}			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;		
	}
	
	public static double calculateReimbursement(int em_id) {
		double totalFunds = getAv_Funds(em_id);
		//find the 2 below vars
		double pendingReimburstments = 0;
		double awardedReimburstments = 0;
		double availableReimburstment = totalFunds - pendingReimburstments - awardedReimburstments;
		
		return availableReimburstment;
	}
	
	
	public static boolean updateCaculatedFunds(int em_id, int price, int percentage) {
		
		double totalFunds = getAv_Funds(em_id);
		
		double pendingReimburstments = (price * percentage)/100;
		
		double awardedReimburstments = totalFunds - pendingReimburstments;
		
		if(pendingReimburstments >= totalFunds) {
			pendingReimburstments = totalFunds;
		}
		
		if (awardedReimburstments <= -1) {
			totalFunds = 0;
		}
		
		try {
				String sql = "UPDATE employees SET av_funds = ? WHERE em_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, Double.toString(awardedReimburstments));
				ps.setString(2, Integer.toString(em_id));
				ps.executeQuery();			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean updateCaculatedFundsRejected(int em_id, int price, int percentage ) {
		
		double totalFunds = getAv_Funds(em_id);
		
		double pendingReimburstments = (price * percentage)/100;
		
		
			
		double awardedReimburstments = totalFunds + pendingReimburstments;
		
		if(pendingReimburstments >= totalFunds) {
			pendingReimburstments = totalFunds;
		}
		
		if (awardedReimburstments <= -1) {
			totalFunds = 0;
		}
		
		
		try {
				String sql = "UPDATE employees SET av_funds = ? WHERE em_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, Double.toString(awardedReimburstments));
				ps.setString(2, Integer.toString(em_id));
				ps.executeQuery();			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	


}

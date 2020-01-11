package webService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import service.EmployeeService;

public class EmployeeWebService {
	
	public static void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee empl = EmployeeService.getEmployee(username, password);
		
		String f_name = empl.getFirst_name();
		String l_name = empl.getLast_name();
		
		int id = empl.getEm_id();
		int supervisor = empl.getSupervisor();
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String employeeJSON = om.writeValueAsString(empl);
			response.getWriter().append(employeeJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
					
		HttpSession session = request.getSession();

		session.setAttribute("username", username);
		session.setAttribute("f_name", f_name);
		session.setAttribute("l_name", l_name);
		session.setAttribute("supervisor", supervisor);
		session.setAttribute("id", id);
		//session.setAttribute("av_funds", av_funds);
		String uname = (String) session.getAttribute("username");
	}
	
	public static void welcome(HttpServletRequest request, HttpServletResponse response) {
		
		 HttpSession session = request.getSession();
	        String name = (String) session.getAttribute("f_name");
	        System.out.println(name);
	        PrintWriter out;
	        try {
	            out = response.getWriter();
	            out.write(name);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		
		Employee employee = EmployeeService.getEmployee(em_id);
		int supervisor = employee.getSupervisor();
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String employeeJSON = om.writeValueAsString(employee);
			PrintWriter out = response.getWriter();
			out.append(employeeJSON);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void getFunds(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		double funds = EmployeeService.getAv_Funds(id);
		ObjectMapper om = new ObjectMapper();
				
		try {
			String fundsJSON = om.writeValueAsString(funds);
			PrintWriter out = response.getWriter();
			out.append(fundsJSON);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}

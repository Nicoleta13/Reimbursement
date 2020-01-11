package webService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import model.Message;
import service.EmployeeService;
import service.MessageService;

public class MsgWebService {
	
	public static void addMessage(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		//int r_id = Integer.parseInt(request.getParameter("r_id"));
	
		List<Employee> subordinates = EmployeeService.getSubordinates(em_id);
		
		EmployeeService.getSubordinates(em_id).forEach(System.out::println);
		int employeeID = 0;
		for(int i = 0; i < subordinates.size(); i++){
			employeeID = subordinates.get(i).getEm_id();
		}

		String message= request.getParameter("message");

		Message msg = new Message(employeeID, em_id, message);
		MessageService.addMessage(msg);
		
		try {
			response.getWriter().append("Message is added to the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void fromBenCoMsg(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		int sender = (int) session.getAttribute("id");
		int receiver = Integer.parseInt(request.getParameter("em_id"));

		String message= request.getParameter("message");

		Message msg = new Message(receiver, sender, message);
		MessageService.addMessage(msg);
		
		try {
			response.getWriter().append("Message is added to the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void answerMessage(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		int sender = (int) session.getAttribute("id");
		int receiver = Integer.parseInt(request.getParameter("em_id"));

		String message= request.getParameter("message");

		Message msg = new Message(receiver, sender, message);
		MessageService.addMessage(msg);
		
		try {
			response.getWriter().append("Message is added to the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void  getAllMessages(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		
		List<Message> allMessages = MessageService.getAllMessages(em_id);
		
		Employee employee = EmployeeService.getEmployee(em_id);
		
		String firstName = employee.getFirst_name();
		String lastName = employee.getLast_name();
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String unreadMsg = om.writeValueAsString(allMessages);
			PrintWriter out = response.getWriter();
			out.append(unreadMsg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void  getBencoMsges(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		
		List<Message> allMessages = MessageService.getAllMessages(em_id);
		
		Employee employee = EmployeeService.getEmployee(em_id);		
		String firstName = employee.getFirst_name();
		String lastName = employee.getLast_name();
		
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String unreadMsg = om.writeValueAsString(allMessages);
			PrintWriter out = response.getWriter();
			out.append(unreadMsg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateMessage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		int sender = Integer.parseInt(request.getParameter("sender"));

		
		MessageService.updateMessage(em_id, sender);
		
		try {
			response.getWriter().append("Message status is update in the database");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

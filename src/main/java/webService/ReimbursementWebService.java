package webService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import model.Event;
import model.EventType;
import model.Reimbursement;
import service.ALLEventsService;
import service.EmployeeService;
import service.EventService;
import service.ReimbursementService;

public class ReimbursementWebService {
	
	public static void addReimbursement(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		
		String fName= request.getParameter("f_name");
		String lName= request.getParameter("l_name");
		String date= request.getParameter("dates");
		String time= request.getParameter("time");
		String locat= request.getParameter("location");
		String desc= request.getParameter("description");
		int g_format= Integer.parseInt(request.getParameter("gr_id"));
		int cost= Integer.parseInt(request.getParameter("cost"));
		int event= Integer.parseInt(request.getParameter("ev_id"));
		//String file_load= request.getParameter("file_load");
	
		ReimbursementService.addReimbursement(em_id, fName, lName, date, time, locat, desc, cost, g_format, event);
		
		try {
			response.getWriter().append("Reimbursement is added to the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void addReimbursementHD(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		
		String fName= request.getParameter("f_name");
		String lName= request.getParameter("l_name");
		String date= request.getParameter("dates");
		String time= request.getParameter("time");
		String locat= request.getParameter("location");
		String desc= request.getParameter("description");
		int g_format= Integer.parseInt(request.getParameter("gr_id"));
		int cost= Integer.parseInt(request.getParameter("cost"));
		int event= Integer.parseInt(request.getParameter("ev_id"));
		//String file_load= request.getParameter("file_load");
	
		ReimbursementService.addReimbursementDH(em_id, fName, lName, date, time, locat, desc, cost, g_format, event);
		
		try {
			response.getWriter().append("Reimbursement is added to the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void getReimsPending(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		List<Employee> subordinates = EmployeeService.getSubordinates(id);
		int employeeID = 0;
		for(int i = 0; i < subordinates.size(); i++){
			employeeID = subordinates.get(i).getEm_id();
		}
		
		List<Reimbursement> reimsPending = ReimbursementService.getReimsPending(employeeID, "pending");
		ObjectMapper om = new ObjectMapper();
				
		try {
			String pendReim = om.writeValueAsString(reimsPending);
			PrintWriter out = response.getWriter();
			out.append(pendReim);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void reimbDHApprove(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		List<Reimbursement> reimsPending = ReimbursementService.getAllReimbursements("wait_for_dHead");

		ObjectMapper om = new ObjectMapper();
				
		try {
			String pendReim = om.writeValueAsString(reimsPending);
			PrintWriter out = response.getWriter();
			out.append(pendReim);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void reimbBenCoApprove(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		List<Reimbursement> reimsPending = ReimbursementService.getAllReimbursements("wait_for_BenCo");

		ObjectMapper om = new ObjectMapper();
				
		try {
			String pendReim = om.writeValueAsString(reimsPending);
			PrintWriter out = response.getWriter();
			out.append(pendReim);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void approvePendingReimb(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int em_id = (int) session.getAttribute("id");
		
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		
		Employee employee = EmployeeService.getEmployee(em_id);
		
		if(employee.getRole().equals("DepHead")) {
			ReimbursementService.updateReimbursement("wait_for_BenCo", r_id);

			
		} else if(employee.getRole().equals("Employee")) {
			ReimbursementService.updateReimbursement("wait_for_dHead", r_id);
			
			//updateFunds
			Reimbursement reimbursement = ReimbursementService.getReimbursement(r_id);
			int  employeeId = reimbursement.getEm_id();
			
			int event_id = reimbursement.getEv_id();
			EventType event = EventService.getEvent(event_id);
			
			int price = event.getPrice();
			int percentage = event.getCoverage_percentage();
			EmployeeService.updateCaculatedFunds(employeeId, price, percentage);
		}
		
		try {
			response.getWriter().append("Reimbursement status is update in the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void approveDepHeadReimb(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		ReimbursementService.updateReimbursement("wait_for_BenCo", r_id);
		
		try {
			response.getWriter().append("Reimbursement status is update in the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void approveBenCoReimb(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		String cost = request.getParameter("cost");
		
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		ReimbursementService.updateReimbursement("approved", r_id);
		
		try {
			response.getWriter().append("Reimbursement status is update in the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void changePrice(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		int cost = Integer.parseInt(request.getParameter("cost"));
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		
		ReimbursementService.updateReimbursementPrice(cost, r_id);
		
		try {
			response.getWriter().append("Reimbursement status is update in the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void denyPendingReimb(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		ReimbursementService.updateReimbursement("rejected", r_id);
		
		//updateFunds
		Reimbursement reimbursement = ReimbursementService.getReimbursement(r_id);
		int  employeeId = reimbursement.getEm_id();
		
		int event_id = reimbursement.getEv_id();
		EventType event = EventService.getEvent(event_id);
		
		int price = event.getPrice();
		int percentage = event.getCoverage_percentage();
		//EmployeeService.updateCaculatedFundsRejected(employeeId, price, percentage);

		
		try {
			response.getWriter().append("Reimbursement hes been removed in the database");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void getAllEvents(HttpServletRequest request, HttpServletResponse response) {
		
		List<Event> events = ALLEventsService.getEvents();;

		ObjectMapper om = new ObjectMapper();
				
		try {
			String pendEvents = om.writeValueAsString(events);
			PrintWriter out = response.getWriter();
			out.append(pendEvents);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

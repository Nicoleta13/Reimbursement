package service;

import model.Message;
import webService.MsgWebService;

public class TEST {

	public static void main(String[] args) {
		
//		System.out.println("====== Get Employee by id =====");
//		System.out.println(EmployeeService.getEmployee(8));
		
//		System.out.println("==== Get Employee by username/password ====");
//		System.out.println(EmployeeService.getEmployee("julie", "julie1"));
		
//		System.out.println("========= Get all Employees======");
//		EmployeeService.getEmployees().forEach(System.out::println);
		
//		System.out.println("========= Get all subordinates======");
//		EmployeeService.getSubordinates(18).forEach(System.out::println);
		
//		System.out.println("========= Add Employees======");
//		Employee empl = new Employee(11, 1, "dann", "dann1", "Dann", "Grey", "dann@gmail.com", "Employee");
//		EmployeeService.addEmployee(empl);
		
//		System.out.println("========= Get Employee's av_funds======");
//		System.out.println(EmployeeService.getEmployee(26));
//		System.out.println(EmployeeService.getAv_Funds(26));
		
//		System.out.println("====== Update Employee's av_funds on New Year =====");
//		System.out.println(EmployeeService.updateFunds(26));
		
		
		
		System.out.println("========= Add Reimbursement======");
		//ReimbursementService.addReimbursement(16, "Test", "TestT", "12/01/2019", "10:00 AM", "Chicago", null, 200, 1, 15);
		ReimbursementService.addReimbursementDH(20, "Test", "TestT", "12/01/2019", "10:00 AM", "Chicago", null, 200, 2, 12);
		

//		System.out.println("====== Update Reimbursement status =====");
//		ReimbursementService.updateReimbursement("wait_pass_grade", 15);
		
//		System.out.println("====== Get Reimbursements status = 'wait_pass_grade' =====");
//		ReimbursementService.getReimsWait_for_pass_grade(9).forEach(System.out::println);
//		
//		System.out.println("====== Get Reimbursements status = 'pending' =====");
//		ReimbursementService.getReimsPending(20).forEach(System.out::println);
//		
//		System.out.println("====== Get clicked Reimbursements =====");
//		System.out.println(ReimbursementService.getReimbursement(15));
		
//		System.out.println("========= Add Messages======");
//		Message msg = new Message(25, 9, "Test Message");
//		MessageService.addMessage(msg);
		
//		System.out.println("========= Get Messages======");
//		MessageService.getAllMessages(16).forEach(System.out::println);
		
		
//		System.out.println("========= Get all Events======");
//		ALLEventsService.getEvents().forEach(System.out::println);
//		
//		
//		System.out.println("========= Read messages======");
//		MessageService.updateMessage(24, 18);
	}

}

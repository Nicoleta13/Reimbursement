package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReimbursementService;
import webService.EmployeeWebService;
import webService.MsgWebService;
import webService.ReimbursementWebService;


public class RequestHelper {
	
	
	public static void process(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		
		//System.out.println(uri);
		
		switch(uri) {
		
			case "/Reimbursement/login.do": {
				EmployeeWebService.login(request, response);
				break;
			}
			case "/Reimbursement/welcome.do": {
				EmployeeWebService.welcome(request, response);
				break;
			}
			case "/Reimbursement/getEmployee.do": {
				EmployeeWebService.getEmployee(request, response);
				break;
			}
			case "/Reimbursement/getFunds.do": {
				EmployeeWebService.getFunds(request, response);
				break;
			}
			case "/Reimbursement/addReimbursement.do": {
				ReimbursementWebService.addReimbursement(request, response);
				break;
			}
			case "/Reimbursement/addReimbursementHD.do": {
				ReimbursementWebService.addReimbursementHD(request, response);
				break;
			}
			case "/Reimbursement/getReimsPending.do": {
				ReimbursementWebService.getReimsPending(request, response);
				break;
			}
			case "/Reimbursement/reimbDHApprove.do": {
				ReimbursementWebService.reimbDHApprove(request, response);
				break;
			}
			case "/Reimbursement/reimbBenCoApprove.do": {
				ReimbursementWebService.reimbBenCoApprove(request, response);
				break;
			}
			case "/Reimbursement/approvePendingReimb.do": {
				ReimbursementWebService.approvePendingReimb(request, response);
				break;
			}
			case "/Reimbursement/approveDepHeadReimb.do": {
				ReimbursementWebService.approveDepHeadReimb(request, response);
				break;
			}
			case "/Reimbursement/approveBenCoReimb.do": {
				ReimbursementWebService.approveBenCoReimb(request, response);
				break;
			}
			case "/Reimbursement/denyPendingReimb.do": {
				ReimbursementWebService.denyPendingReimb(request, response);
				break;
			}
			case "/Reimbursement/addMessage.do": {
				MsgWebService.addMessage(request, response);
				break;
			}
			case "/Reimbursement/answerMessage.do": {
				MsgWebService.answerMessage(request, response);
				break;
			}
			case "/Reimbursement/fromBenCoMsg.do": {
				MsgWebService.fromBenCoMsg(request, response);
				break;
			}
			case "/Reimbursement/getAllMessages.do": {
				MsgWebService.getAllMessages(request, response);
				break;
			}
			case "/Reimbursement/getBencoMsges.do": {
				MsgWebService.getBencoMsges(request, response);
				break;
			}
			case "/Reimbursement/changePrice.do": {
				ReimbursementWebService.changePrice(request, response);
				break;
			}
			case "/Reimbursement/getAllEvents.do": {
				ReimbursementWebService.getAllEvents(request, response);
				break;
			}
			case "/Reimbursement/updateMessage.do": {
				MsgWebService.updateMessage(request, response);
				break;
			}
			default: {
				try {
					response.sendError(451, "Get off my lawn!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

package service;

import java.util.List;

import DAO.ReimbursementDAO;
import DAO.ReimbursementDAOImpl;
import model.Reimbursement;

public class ReimbursementService {
	public static ReimbursementDAO reimbursement = new ReimbursementDAOImpl();
	
	public static boolean addReimbursement(int em_id, String f_name, String l_name, String dates, String time, 
			String location, String description, int cost, int gr_id, int ev_id) {
		return reimbursement.addReimbursement(em_id, f_name, l_name, dates, time, location,
				description, cost, gr_id, ev_id);
	}
	
	public static boolean addReimbursementDH(int em_id, String f_name, String l_name, String dates, String time, 
			String location, String description, int cost, int gr_id, int ev_id) {
		return reimbursement.addReimbursementDH(em_id, f_name, l_name, dates, time, location, description, cost, gr_id, ev_id);
	}
	
	public static boolean updateReimbursement(String status, int r_id) {
		return reimbursement.updateReimbursement(status, r_id);
	}
	public static boolean updateReimbursementPrice(int cost, int r_id) {
		return reimbursement.updateReimbursementPrice(cost, r_id);
	}
	
	
	
	public static List<Reimbursement> getReimsPending(int em_id, String status){
		return reimbursement.getReimsPending(em_id, status);
	}
	
	public static List<Reimbursement> getReimsWait_for_pass_grade(int em_id){
		return reimbursement.getReimsWait_for_pass_grade(em_id);
	}
	
	public static List<Reimbursement> getAllReimbursements(String status){
		return reimbursement.getAllReimbursements(status);
	}
	
	public static Reimbursement getReimbursement(int r_id) {
		return reimbursement.getReimbursement(r_id);
	}
	

}

package DAO;

import java.util.List;

import model.Reimbursement;

public interface ReimbursementDAO {

	public boolean addReimbursement(int em_id, String f_name, String l_name, String dates, String time, 
			String location, String description, int cost, int gr_id, int ev_id);
	
	public boolean addReimbursementDH(int em_id, String f_name, String l_name, String dates, String time, 
			String location, String description, int cost, int gr_id, int ev_id);
	
	public Reimbursement getReimbursement(int r_id);
	
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getAllReimbursements(String status);
	
	public List<Reimbursement> getReimsPending(int em_id, String status);
	
	public List<Reimbursement> getReimsWait_for_pass_grade(int em_id);
	
	public boolean updateReimbursement(String status, int r_id);
	
	public boolean updateReimbursementPrice(int cost, int r_id);
	
	public boolean deleteReimbursement();
}

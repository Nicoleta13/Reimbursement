package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reimbursement;
import util.JDBCConnection;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean addReimbursement(int em_id, String f_name, String l_name, String dates, String time, 
			String location, String description, int cost, int gr_id, int ev_id) {
		
		
		try {
			String sql = "CALL add_reimbursement(?,?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
					
				cs.setInt(1, em_id);
				cs.setString(2, f_name);
				cs.setString(3, l_name);
				cs.setString(4, dates);
				cs.setString(5, time);
				cs.setString(6, location);
				cs.setString(7, description);
				cs.setInt(8, cost);
				cs.setInt(9, gr_id);
				cs.setInt(10, ev_id);
				//cs.setString(11, file_load);
					
				cs.execute();
			return true;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	public boolean addReimbursementDH(int em_id, String f_name, String l_name, String dates, String time, 
			String location, String description, int cost, int gr_id, int ev_id) {
		
		
		try {
			String sql = "CALL add_reimbursementDH(?,?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
					
				cs.setInt(1, em_id);
				cs.setString(2, f_name);
				cs.setString(3, l_name);
				cs.setString(4, dates);
				cs.setString(5, time);
				cs.setString(6, location);
				cs.setString(7, description);
				cs.setInt(8, cost);
				cs.setInt(9, gr_id);
				cs.setInt(10, ev_id);
				//cs.setString(11, file_load);
					
				cs.execute();
			return true;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	//get the clicked reimbursement 
	@Override
	public Reimbursement getReimbursement(int r_id) {
		
		try {
			String sql = "SELECT * FROM reimbursement WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(r_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				return (new Reimbursement(
						rs.getInt("r_id"),
						rs.getInt("em_id"),
						rs.getString("f_name"),
						rs.getString("l_name"),
						rs.getString("dates"),
						rs.getString("time"),
						rs.getString("location"),
						rs.getString("description"),
						rs.getInt("cost"),
						rs.getInt("gr_id"),
						rs.getInt("ev_id")));
						//rs.getString("file_load")));
			}					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		
		return null;
	}
	
	//used when by an employee to see his approved reimbursement to provide a grade or presentation
	public List<Reimbursement> getReimsWait_for_pass_grade(int em_id){
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			String sql = "SELECT * FROM reimbursement WHERE em_id = ? AND status = 'wait_pass_grade'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(em_id));
			
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(
						rs.getInt("r_id"),
						rs.getInt("em_id"),
						rs.getString("f_name"),
						rs.getString("l_name"),
						rs.getString("dates"),
						rs.getString("time"),
						rs.getString("location"),
						rs.getString("description"),
						rs.getInt("cost"),
						rs.getInt("gr_id"),
						rs.getInt("ev_id")));
						//rs.getString("file_load")));
			}		
			
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Reimbursement> getAllReimbursements(String status){
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			String sql = "SELECT * FROM reimbursement WHERE status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(
						rs.getInt("r_id"),
						rs.getInt("em_id"),
						rs.getString("f_name"),
						rs.getString("l_name"),
						rs.getString("dates"),
						rs.getString("time"),
						rs.getString("location"),
						rs.getString("description"),
						rs.getInt("cost"),
						rs.getInt("gr_id"),
						rs.getInt("ev_id")));
						//rs.getString("file_load")));
			}		
			
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Reimbursement> getReimsPending(int em_id, String status){
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			String sql = "SELECT * FROM reimbursement WHERE em_id = ? AND status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(em_id));
			ps.setString(2, status);
			
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(
						rs.getInt("r_id"),
						rs.getInt("em_id"),
						rs.getString("f_name"),
						rs.getString("l_name"),
						rs.getString("dates"),
						rs.getString("time"),
						rs.getString("location"),
						rs.getString("description"),
						rs.getInt("cost"),
						rs.getInt("gr_id"),
						rs.getInt("ev_id")));
						//rs.getString("file_load")));
			}		
			
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//used to set the status to different states 
	public boolean updateReimbursement(String status, int r_id) {
		String sql = "UPDATE reimbursement SET status = ? WHERE r_id = ?";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, Integer.toString(r_id));
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteReimbursement() {
		
		return false;
	}
	
	public boolean updateReimbursementPrice(int cost, int r_id) {
		String sql = "UPDATE reimbursement SET cost = ? WHERE r_id = ?";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(cost));
			ps.setString(2, Integer.toString(r_id));
			ps.executeQuery();
		
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public boolean deleteReimbursement() {
//		
//		return false;
//	}

}

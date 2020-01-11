package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Message;
import model.Reimbursement;
import util.JDBCConnection;

public class MessageDAOImpl implements MessageDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean addMessage(Message msg) {
		try {
			String sql = "CALL add_message(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			//cs.setInt(1, msg.getR_id());
			cs.setInt(1, msg.getEm_id());
			cs.setInt(2, msg.getSender());
			cs.setString(3, msg.getMessage());
			
			cs.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Message> getAllMessages(int em_id){
		
		List<Message> messages = new ArrayList<Message>();
		
		try {
			String sql = "SELECT * FROM messages WHERE em_id = ? AND status = 'unread'";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(em_id));
			
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				messages.add(new Message(
						//rs.getInt("r_id"),
						rs.getInt("em_id"),
						rs.getInt("sender"),
						rs.getString("message")));
			}		
			
			return messages;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean updateMessage(int receiver, int sender) {
		
		try {
			String sql = "UPDATE messages SET status = 'read' WHERE em_id = ? AND sender = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(receiver));
			ps.setString(2, Integer.toString(sender));
			
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}

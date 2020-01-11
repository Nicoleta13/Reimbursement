package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EventType;
import util.JDBCConnection;

public class EventTypeDAOImpl implements EventTypeDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public EventType getEvent(int ev_id) {
		
		try {
			String sql = "SELECT * FROM event_types WHERE ev_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(ev_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				
				return (new EventType(
						rs.getInt("EV_ID"),
						rs.getString("EVENT_TYPE"),
						rs.getInt("PRICE"),
						rs.getInt("coverage_percentage")));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

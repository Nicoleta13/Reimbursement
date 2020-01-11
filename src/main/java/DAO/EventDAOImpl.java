package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import util.JDBCConnection;

public class EventDAOImpl implements EventDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public List<Event> getEvents() {
		
		List<Event> events = new ArrayList<Event>();
		
		try {
			String sql = "SELECT * FROM events";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				events.add(new Event(
						rs.getInt("ev_id"),
						rs.getString("e_name"),
						rs.getString("date_start"),
						rs.getString("date_end"),
						rs.getInt("price")));
			}		
			return events;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

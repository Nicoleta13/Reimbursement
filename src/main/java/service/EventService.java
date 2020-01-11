package service;

import DAO.EventTypeDAO;
import DAO.EventTypeDAOImpl;
import model.EventType;

public class EventService {
	
	public static EventTypeDAO events = new EventTypeDAOImpl();
	
	public static EventType getEvent(int ev_id) {
		return events.getEvent(ev_id);
	}

}

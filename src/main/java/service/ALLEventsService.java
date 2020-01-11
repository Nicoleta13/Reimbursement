package service;

import java.util.List;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import model.Event;

public class ALLEventsService {
	
public static EventDAO events = new EventDAOImpl();
	
public static List<Event> getEvents() {
		return events.getEvents();
	}

}

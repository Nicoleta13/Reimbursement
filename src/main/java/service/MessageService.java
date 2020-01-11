package service;

import java.sql.Connection;
import java.util.List;

import DAO.MessageDAO;
import DAO.MessageDAOImpl;
import model.Message;
import util.JDBCConnection;

public class MessageService {
	
	public static MessageDAO messages = new MessageDAOImpl();
	
	public static Connection conn = JDBCConnection.getConnection();
	
	public static boolean addMessage(Message msg) {
		return messages.addMessage(msg);
	}
	
	public static List<Message> getAllMessages(int em_id){
		return messages.getAllMessages(em_id);
	}

	public static boolean updateMessage(int receiver, int sender) {
		return messages.updateMessage(receiver, sender);
	}
}

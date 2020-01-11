package DAO;

import java.util.List;

import model.Message;

public interface MessageDAO {
	
	public boolean addMessage(Message msg);
	
	public List<Message> getAllMessages(int em_id);
	
	public boolean updateMessage(int receiver,int sender);

}

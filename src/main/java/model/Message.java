package model;

public class Message {
	
	//private int r_id;
	private int em_id;
	private int sender;
	private String message;
	
//	public Message(int r_id, int em_id, int sender, String message) {
//		super();
//		this.r_id = r_id;
//		this.em_id = em_id;
//		this.sender = sender;
//		this.message = message;
//	}
	
	public Message(int em_id, int sender, String message) {
		super();
		this.em_id = em_id;
		this.sender = sender;
		this.message = message;
	}

//	public int getR_id() {
//		return r_id;
//	}
//
//	public void setR_id(int r_id) {
//		this.r_id = r_id;
//	}

	public int getEm_id() {
		return em_id;
	}

	

	public void setEm_id(int em_id) {
		this.em_id = em_id;
	}
	

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [em_id=" + em_id + ", sender=" + sender + ", message=" + message + "]";
	}

	
	
	
}

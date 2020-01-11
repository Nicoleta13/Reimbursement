package model;

public class Event {
	
	int ev_id;
    String event_name;
    String start_date;
    String end_date;
    int price;
    int coverage_percentage;
    
    
	public Event(int ev_id, String event_name, String start_date, String end_date, int price) {
		super();
		this.ev_id = ev_id;
		this.event_name = event_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.price = price;
	}
	
	
	
	public Event(int ev_id, String event_name, String start_date, String end_date, int price, int coverage_percentage) {
		super();
		this.ev_id = ev_id;
		this.event_name = event_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.price = price;
		this.coverage_percentage = coverage_percentage;
	}



	public int getEv_id() {
		return ev_id;
	}
	public void setEv_id(int ev_id) {
		this.ev_id = ev_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCoverage_percentage() {
		return coverage_percentage;
	}
	public void setCoverage_percentage(int coverage_percentage) {
		this.coverage_percentage = coverage_percentage;
	}
	@Override
	public String toString() {
		return "Event [ev_id=" + ev_id + ", event_name=" + event_name + ", start_date=" + start_date + ", end_date="
				+ end_date + ", price=" + price + ", coverage_percentage=" + coverage_percentage + "]";
	}
    
    
    

}

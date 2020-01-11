package model;

public class EventType {
	
	int ev_id;
    String event_type;
    int price;
    int coverage_percentage;
    
	public EventType(int ev_id, String event_type, int price, int coverage_percentage) {
		super();
		this.ev_id = ev_id;
		this.event_type = event_type;
		this.price = price;
		this.coverage_percentage = coverage_percentage;
	}

	public int getEv_id() {
		return ev_id;
	}

	public void setEv_id(int ev_id) {
		this.ev_id = ev_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
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
		return "Events [ev_id=" + ev_id + ", event_type=" + event_type + ", price=" + price + ", coverage_percentage="
				+ coverage_percentage + "]";
	}
    
    

}



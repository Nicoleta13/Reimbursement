package model;

public class Reimbursement {
	int r_id;
	int em_id;
    String f_name;
    String l_name;
    String dates;
    String time;
    String location;
    String description;
    int cost;
    int gr_id;
    int ev_id;
    //String file_load;
    
    
    
	public Reimbursement(int r_id, int em_id, String f_name, String l_name, String dates, String time, String location,
			String description, int cost, int gr_id, int ev_id) {
		super();
		this.r_id = r_id;
		this.em_id = em_id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.dates = dates;
		this.time = time;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gr_id = gr_id;
		this.ev_id = ev_id;
		//this.file_load = file_load;
	}



	public Reimbursement(int em_id, String f_name, String l_name, String dates, String time, String location,
			String description, int cost, int gr_id, int ev_id) {
		super();
		this.em_id = em_id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.dates = dates;
		this.time = time;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gr_id = gr_id;
		this.ev_id = ev_id;
		//this.file_load = file_load;
	}
	
	
	public Reimbursement(String f_name, String l_name, String dates, String time, String location,
			String description, int cost, int gr_id, int ev_id) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
		this.dates = dates;
		this.time = time;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gr_id = gr_id;
		this.ev_id = ev_id;
		//this.file_load = file_load;
	}
	
	

	public int getR_id() {
		return r_id;
	}


	public void setR_id(int r_id) {
		this.r_id = r_id;
	}




	public int getEm_id() {
		return em_id;
	}


	public void setEm_id(int em_id) {
		this.em_id = em_id;
	}


	public String getF_name() {
		return f_name;
	}


	public void setF_name(String f_name) {
		this.f_name = f_name;
	}


	public String getL_name() {
		return l_name;
	}


	public void setL_name(String l_name) {
		this.l_name = l_name;
	}


	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getGr_id() {
		return gr_id;
	}


	public void setGr_id(int gr_id) {
		this.gr_id = gr_id;
	}


	public int getEv_id() {
		return ev_id;
	}


	public void setEv_id(int ev_id) {
		this.ev_id = ev_id;
	}

//	public String getFile_load() {
//	return file_load;
//}
//
//
//
//public void setFile_load(String file_load) {
//	this.file_load = file_load;
//}

	@Override
	public String toString() {
		return "Reimbursement [r_id=" + r_id + ", em_id=" + em_id + ", f_name=" + f_name + ", l_name=" + l_name
				+ ", dates=" + dates + ", time=" + time + ", location=" + location + ", description=" + description
				+ ", cost=" + cost + ", gr_id=" + gr_id + ", ev_id=" + ev_id + "]";
	}
 

}

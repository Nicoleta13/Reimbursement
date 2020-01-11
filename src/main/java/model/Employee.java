package model;

public class Employee {
	
	private int em_id;
	private int supervisor;
	private int dep_id;
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private String role;
	private double av_funds;
    
    
	public Employee() {
		super();
	}
	
	public Employee(int supervisor, int dep_id, String username, String password, String first_name,
			String last_name, String email, String role, double av_funds) {
		super();
		this.em_id = em_id;
		this.supervisor = supervisor;
		this.dep_id = dep_id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.role = role;
		this.av_funds = av_funds;
	}


	public Employee(int em_id, int supervisor, int dep_id, String username, String password, String first_name,
			String last_name, String email, String role, double av_funds) {
		super();
		this.em_id = em_id;
		this.supervisor = supervisor;
		this.dep_id = dep_id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.role = role;
		this.av_funds = av_funds;
	}


	public int getEm_id() {
		return em_id;
	}


	public void setEm_id(int em_id) {
		this.em_id = em_id;
	}


	public int getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}


	public int getDep_id() {
		return dep_id;
	}


	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	


	public double getAv_funds() {
		return av_funds;
	}

	public void setAv_funds(double av_funds) {
		this.av_funds = av_funds;
	}

	@Override
	public String toString() {
		return "Employee [em_id=" + em_id + ", supervisor=" + supervisor + ", dep_id=" + dep_id + ", username="
				+ username + ", password=" + password + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", role=" + role + ", av_funds=" + av_funds + "]";
	}

	
	
    
}

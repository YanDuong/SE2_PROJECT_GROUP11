package net.se2project.covidtracker.model;

public class Admin {
	protected int admin_id;
	protected String name;
	protected String email;
	protected String password;
	
	public Admin() {
	}
	public Admin(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Admin(int admin_id, String name, String email, String password) {
		super();
		this.admin_id = admin_id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int user_id) {
		this.admin_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}

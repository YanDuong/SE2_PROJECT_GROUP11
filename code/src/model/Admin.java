package model;


public class Admin {
    private int adminId;
    private String adminName;
    private String password;
    private java.sql.Date birthDate;
    private Gender gender;
    private String email;
    private String address;
    private String phoneNumber;

    public Admin() {

    }
    public Admin(int adminId, String adminName, String password, java.sql.Date birthDate, Gender gender, String email, String phoneNumber, String address) {
       this.adminId = adminId;
       this.adminName = adminName;
       this.password = password;
       this.birthDate = birthDate;
       this.gender = gender;
       this.email = email;
       this.phoneNumber = phoneNumber;
       this.address = address;
       

    }
public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getAdminId() {
        return adminId;
    }
    public String getAdminName() {
        return adminName;
    }
    public String getPassowrd() {
        return password;
    }
    public java.sql.Date getBirthDate() {
        return birthDate;
    }
    public Gender getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    
   
}



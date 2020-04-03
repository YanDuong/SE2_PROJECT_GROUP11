package dao;

public interface AdminInterface {
boolean validateAdmin(String adminName, String password);
boolean checkAdmin(String adminName);
}

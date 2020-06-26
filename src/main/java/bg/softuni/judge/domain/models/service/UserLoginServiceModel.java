package bg.softuni.judge.domain.models.service;

public class UserLoginServiceModel {
	private String username;
	private String password;
	
	
	public UserLoginServiceModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public UserLoginServiceModel() {
		
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
}

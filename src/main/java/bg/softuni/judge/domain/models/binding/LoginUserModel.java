package bg.softuni.judge.domain.models.binding;

import javax.validation.constraints.Size;

public class LoginUserModel {
	@Size(min = 2,max = 10 , message = "Username must be between 2 and 10 characters")
	private String username;
	@Size(min = 3, message = "Password must be at least 3 characters")
	private String password;
	
	public LoginUserModel(String username, String password) {
		this.username = username;
		this.password = password;
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

	
	
}

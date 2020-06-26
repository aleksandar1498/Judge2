package bg.softuni.judge.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import bg.softuni.judge.enums.Roles;

public class UserAddModel {
	@NotEmpty(message = "You have to select a username")
	private String username;
	
	@NotNull(message = "The role is mandatory")
	private Roles role;

	public UserAddModel() {
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserAddModel [username=" + username + ", role=" + role + "]";
	}

}

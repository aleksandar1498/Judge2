package bg.softuni.judge.domain.models.service;

import java.util.List;
import java.util.Set;

public class UserServiceModel {
	private String id;

	private String username;

	private String password;

	private String confirmPassword;

	private String email;

	private String git;

	private Set<RoleServiceModel> roles;

	public UserServiceModel() {
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getGit() {
		return git;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGit(String git) {
		this.git = git;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<RoleServiceModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleServiceModel> roles) {
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserServiceModel [username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", email=" + email + ", git=" + git + ", roles=" + roles + "]";
	}

}

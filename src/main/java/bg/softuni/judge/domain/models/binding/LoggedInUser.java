package bg.softuni.judge.domain.models.binding;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import bg.softuni.judge.domain.models.service.RoleServiceModel;
import bg.softuni.judge.enums.Roles;

public class LoggedInUser implements Serializable {

	private static final long serialVersionUID = 5760871282417018588L;

	private String id; 
	
	private String username;

	private String email;

	private String git;

	private Set<Roles> roles;

	public LoggedInUser() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGit() {
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}

	public Set<Roles> getRoles() {
		return roles;
	}
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoggedInUser [username=" + username + ", email=" + email + ", git=" + git + ", role=" + roles + "]";
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	

}

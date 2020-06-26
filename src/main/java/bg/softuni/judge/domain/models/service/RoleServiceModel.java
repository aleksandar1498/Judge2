package bg.softuni.judge.domain.models.service;

import bg.softuni.judge.enums.Roles;

public class RoleServiceModel {
	private String id;
	private Roles role;
	
	public RoleServiceModel() {
	}

	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RoleServiceModel [id=" + id + ", role=" + role + "]";
	}
	
	
	
}

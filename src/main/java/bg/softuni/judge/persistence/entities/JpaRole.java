package bg.softuni.judge.persistence.entities;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import bg.softuni.judge.enums.Roles;
import bg.softuni.judge.persistence.Role;

@Entity
@Table(name = "roles")
public class JpaRole implements Role {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Enumerated(EnumType.STRING)
	private Roles role;

	public JpaRole() {
		// needed by JPA
	}

	public JpaRole(Roles role) {
		this.role = role;
	}

	public JpaRole(String id, Roles role) {
		this.role = role;
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getRole() {
		return role.toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "JpaRole [id=" + id + ", role=" + role + "]";
	}

}

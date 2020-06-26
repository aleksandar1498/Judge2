package bg.softuni.judge.persistence.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import bg.softuni.judge.persistence.Homework;
import bg.softuni.judge.persistence.Role;
import bg.softuni.judge.persistence.User;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class JpaUser implements User {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String git;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = JpaRole.class)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;
	
    @OneToMany(mappedBy = "author",targetEntity = JpaHomework.class,cascade = CascadeType.ALL)
    private List<Homework> homeworks;

	public JpaUser() {
		// needed by JPA
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getGit() {
		return git;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGit(String git) {
		this.git = git;
	}

	@Override
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "JpaUser [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", git="
				+ git + ", roles=" + roles + "]";
	}


	@Override
	public List<Homework> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(List<Homework> homeworks) {
		this.homeworks = homeworks;
	}
	
	

}

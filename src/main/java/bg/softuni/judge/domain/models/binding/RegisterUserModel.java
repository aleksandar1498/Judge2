package bg.softuni.judge.domain.models.binding;


import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;


public class RegisterUserModel{
	
	@NotEmpty(message = "The username cannot be empty")
	@Size(min = 2,max = 10 , message = "Username must be between 2 and 10 characters")
	private String username;
	
	@Size(min = 3, message = "Password must be at least 3 characters")
	@NotEmpty(message = "The password cannot be empty")
	private String password;
	
	@NotEmpty(message = "The confirm password cannot be empty")
	private String confirmPassword;
	
	@Email
	@NotEmpty(message = "The email cannot be empty")
	private String email;
	
	@Pattern(regexp = "https:\\/github\\.com\\/.+\\/.+\\/",message = "Incorrect git address format or empty")
	private String git;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGit(String gitAddress) {
		this.git = gitAddress;
	}


}

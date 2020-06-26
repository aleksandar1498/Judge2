package bg.softuni.judge.domain.models.service;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class HomeworkServiceModel {

	@NotEmpty
	private ExerciseServiceModel exercise;

	@Pattern(regexp = "https:\\/github\\.com\\/.+\\/.+\\/", message = "Incorrect git address format or empty")
	private String gitAddress;

	private UserServiceModel author;

	private LocalDateTime addedOn;

	public ExerciseServiceModel getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseServiceModel exercise) {
		this.exercise = exercise;
	}

	public String getGitAddress() {
		return gitAddress;
	}

	public void setGitAddress(String gitAddress) {
		this.gitAddress = gitAddress;
	}

	public UserServiceModel getAuthor() {
		return author;
	}

	public void setAuthor(UserServiceModel author) {
		this.author = author;
	}

	public LocalDateTime getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(LocalDateTime addedOn) {
		this.addedOn = addedOn;
	}

	@Override
	public String toString() {
		return "HomeworkServiceModel [exercise=" + exercise + ", gitAddress=" + gitAddress + ", author=" + author + "]";
	}

}

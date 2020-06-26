package bg.softuni.judge.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import bg.softuni.judge.domain.models.service.ExerciseServiceModel;

public class HomeworkAddModel {
	
	@NotEmpty
	private String exerciseId;
	
	@Pattern(regexp = "https:\\/github\\.com\\/.+\\/.+\\/",message = "Incorrect git address format or empty")
	private String gitAddress;
	
	public HomeworkAddModel() {
	}

	public String getExercise() {
		return exerciseId;
	}

	public void setExercise(String exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getGitAddress() {
		return gitAddress;
	}

	public void setGitAddress(String gitAddress) {
		this.gitAddress = gitAddress;
	}

	@Override
	public String toString() {
		return "HomeworkAddModel [exercise=" + exerciseId + ", gitAddress=" + gitAddress + "]";
	}
	
	
	
	
}

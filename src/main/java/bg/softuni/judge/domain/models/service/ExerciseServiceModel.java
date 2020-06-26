package bg.softuni.judge.domain.models.service;

import java.time.LocalDateTime;


public class ExerciseServiceModel {
	
	private String id;
	private String name;
	private LocalDateTime startedOn;
	private LocalDateTime dueDate;

	public ExerciseServiceModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "ExerciseServiceModel [id=" + id + ", name=" + name + ", startedOn=" + startedOn + ", dueDate=" + dueDate
				+ "]";
	}

	
	

}

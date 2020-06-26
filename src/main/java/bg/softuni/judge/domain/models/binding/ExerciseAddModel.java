package bg.softuni.judge.domain.models.binding;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ExerciseAddModel {

	@NotEmpty(message = "Exercise name cannot be empty")
	@Size(min = 2, message = "Exercise name must be at least 2 character")
	private String name;

	@PastOrPresent(message = "The date cannot be in the future")
	@NotNull(message = "Exercise startedOn cannot be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startedOn;

	@FutureOrPresent(message = "The date cannot be in the past")
	@NotNull(message = "Exercise dueDates cannot be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dueDate;

	public ExerciseAddModel() {
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

}

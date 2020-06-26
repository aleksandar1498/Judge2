package bg.softuni.judge.persistence.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import bg.softuni.judge.persistence.Exercise;

@Entity
@Table(name = "exercises")
public class JpaExercise implements Exercise{
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column
	private String name;
	
	@Column
	private LocalDateTime startedOn;
	
	@Column
	private LocalDateTime dueDate;
	
	public JpaExercise() {
		// needed by JPA
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public LocalDateTime getStartedOn() {
		return startedOn;
	}

	@Override
	public LocalDateTime getDueDate() {
		return dueDate;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "JpaExercise [id=" + id + ", name=" + name + ", startedOn=" + startedOn + ", dueDate=" + dueDate + "]";
	}

}

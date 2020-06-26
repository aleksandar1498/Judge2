package bg.softuni.judge.persistence.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import bg.softuni.judge.persistence.Comment;
import bg.softuni.judge.persistence.Exercise;
import bg.softuni.judge.persistence.Homework;
import bg.softuni.judge.persistence.User;

@Entity
@Table(name = "homeworks")
public class JpaHomework implements Homework{

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime addedOn;
	
	@Column
	private String gitAddress;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = JpaUser.class)
    @JoinColumn(name = "author_id")
	private User author;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = JpaExercise.class)
    @JoinColumn(name = "exercise_id")
	private Exercise exercise;
	
	@OneToMany(mappedBy = "homework",cascade = CascadeType.ALL,targetEntity = JpaComment.class)
	private List<Comment> comments;

	public JpaHomework() {
		// needed by JPA
	}
	
	@Override
	public String getId() {
	return id;
	}

	@Override
	public LocalDateTime getAddedOn() {
		return addedOn;
	}

	@Override
	public String getGitAddress() {
		return gitAddress;
	}

	@Override
	public User getAuthor() {
		return author;
	}

	@Override
	public Exercise getExercise() {
		return exercise;
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAddedOn(LocalDateTime addedOn) {
		this.addedOn = addedOn;
	}

	public void setGitAddress(String gitAddress) {
		this.gitAddress = gitAddress;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "JpaHomework [id=" + id + ", addedOn=" + addedOn + ", gitAddress=" + gitAddress + ", author=" + author
				+ ", exercise=" + exercise + ", comments=" + comments + "]";
	}

}

/**
 * 
 */
package bg.softuni.judge.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import bg.softuni.judge.persistence.Comment;
import bg.softuni.judge.persistence.Homework;
import bg.softuni.judge.persistence.User;

/**
 * @author AStefanov
 *
 */
@Entity
@Table(name = "comments")
public class JpaComment implements Comment {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column
	private Integer score;
	
	@Column(name = "text_content",columnDefinition = "TEXT")
	private String textContent;
	
	
	@JoinColumn(name = "author_id")
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = JpaUser.class)
	private User author;
	
	@JoinColumn(name = "homework_id")
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = JpaUser.class)
	private Homework homework;

	
	public JpaComment() {
		//needed by JPA
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Integer getScore() {
		return score;
	}

	@Override
	public String getTextContent() {
		return textContent;
	}

	@Override
	public User getAuthor() {
		return author;
	}

	@Override
	public Homework getHomework() {
		return homework;
	}

}

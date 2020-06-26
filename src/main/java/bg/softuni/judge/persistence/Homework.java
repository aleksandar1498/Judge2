package bg.softuni.judge.persistence;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AStefanov
 *
 */
public interface Homework {
	/**
	 * @return
	 */
	String getId();
	/**
	 * @return
	 */
	LocalDateTime getAddedOn();
	/**
	 * @return
	 */
	String getGitAddress();
	/**
	 * @return
	 */
	User getAuthor();
	/**
	 * @return exercise that owns the homework
	 */
	Exercise getExercise();
	List<Comment> getComments();
	
}

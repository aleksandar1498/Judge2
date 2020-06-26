package bg.softuni.judge.persistence;

import java.time.LocalDateTime;

/**
 * @author AStefanov
 *
 */
public interface Exercise {
	/**
	 * @return
	 */
	String getId();
	
	/**
	 * @return
	 */
	String getName();
	
	/**
	 * @return
	 */
	LocalDateTime getStartedOn();
	/**
	 * @return
	 */
	LocalDateTime getDueDate();
}

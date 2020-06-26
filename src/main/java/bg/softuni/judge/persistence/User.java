package bg.softuni.judge.persistence;

import java.util.List;
import java.util.Set;

/**
 * @author AStefanov
 *
 */
public interface User {
	/**
	 * @return
	 */
	

	/**
	 * @return
	 */
	String getUsername();

	/**
	 * @return
	 */
	String getPassword();

	/**
	 * @return
	 */
	String getEmail();

	/**
	 * @return
	 */
	String getGit();

	Set<Role> getRoles();

	List<Homework> getHomeworks();
	
	//List<Homework> getHomeworks();
}

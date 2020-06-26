package bg.softuni.judge.persistence;

public interface Comment {
	String getId();
	Integer getScore();
	String getTextContent();
	User getAuthor();
	Homework getHomework();
}

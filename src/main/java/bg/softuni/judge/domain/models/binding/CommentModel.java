package bg.softuni.judge.domain.models.binding;

public class CommentModel {
	private Integer score;
	private String textContent;

	public CommentModel() {
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	@Override
	public String toString() {
		return "CommentModel [score=" + score + ", textContent=" + textContent + "]";
	}

}

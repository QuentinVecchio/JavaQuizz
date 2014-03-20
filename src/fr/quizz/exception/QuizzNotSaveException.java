package fr.quizz.exception;

public class QuizzNotSaveException extends Exception {

	private static final long serialVersionUID = 3339381559235897870L;

	public QuizzNotSaveException(String message) {
		super(message);
	}
}

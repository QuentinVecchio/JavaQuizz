package fr.quizz.exception;

public class QuestionNotSaveException extends Exception {
	private static final long serialVersionUID = -8855354367314164272L;
	
	public QuestionNotSaveException(String message) {
		super(message);
	}
}

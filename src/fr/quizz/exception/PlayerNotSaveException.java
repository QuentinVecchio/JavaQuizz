package fr.quizz.exception;

public class PlayerNotSaveException extends Exception {
	
	private static final long serialVersionUID = 7091582523034685560L;

	public PlayerNotSaveException(String message) {
		super(message);
	}
}

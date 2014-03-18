package fr.quizz.exception;

public class ItemNotSaveException extends Exception {
	
	private static final long serialVersionUID = -1030825486517948155L;

	public ItemNotSaveException(String message) {
		super(message);
	}
}

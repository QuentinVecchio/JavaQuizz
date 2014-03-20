package fr.quizz.main;

import fr.quizz.controller.PlayerController;

public class Launcher {

	/**
	 * @author Matthieu CLIN, Quentin VECCHIO
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		PlayerController pC = new PlayerController();
		pC.login();
	}

	
	public static void printException(Exception e){
		//e.printStackTrace();
	}
	
}

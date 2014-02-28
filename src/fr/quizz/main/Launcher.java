package fr.quizz.main;

import fr.quizz.controller.PlayerController;

public class Launcher {

	/**
	 * @author Matthieu CLIN, Quentin Vecchio
	 * @param args
	 */
	public static void main(String[] args) {
		
		//PlayerModel pM = new PlayerModel();
		//pM.deletePlayer(new Player(1, "", "",""));
		PlayerController pC = new PlayerController();
		pC.login();
	}

}

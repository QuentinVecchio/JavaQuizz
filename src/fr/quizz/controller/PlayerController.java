package fr.quizz.controller;

import fr.quizz.core.Player;
import fr.quizz.model.PlayerModel;
import fr.quizz.view.player.Login;

public class PlayerController extends Controller {

	PlayerModel model = new PlayerModel();
	
	public PlayerController() {
		
	}
	
	public void login(){
		Login g = new Login(this);
		g.showDialog();
	}
	
	public Player playerExists(String login, String password){
		return model.exist(login, password);
	}

}

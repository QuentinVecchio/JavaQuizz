package fr.quizz.controller;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
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
	
	public Player playerExists(String login, String password) throws DatabaseConnexionException{
		return model.exist(login, password);
	}

	public boolean addPlayer(Player p)
	{
		return true;
	}
	
	public boolean editPlayer(Player p)
	{
		return true;
	}
}

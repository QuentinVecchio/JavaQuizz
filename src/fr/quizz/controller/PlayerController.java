package fr.quizz.controller;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.PlayerNotSaveException;
import fr.quizz.exception.UpdatePlayerException;
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

	public int addPlayer(Player p) throws DatabaseConnexionException, PlayerNotSaveException{
		//TODO ajouter vérification sur les valeurs du player
		return model.savePlayer(p);
	}
	
	public void editPlayer(Player p) throws DatabaseConnexionException, UpdatePlayerException{
		//TODO ajouter verif probablement dans la classe elle-même
		model.updatePlayer(p);
	}
	
	public void deletePlayer(int id) throws DatabaseConnexionException, DeleteMultipleException{
		model.delete(id);
	}
}

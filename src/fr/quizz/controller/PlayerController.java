package fr.quizz.controller;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

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
		//TODO ajouter v�rification sur les valeurs du player
		return model.savePlayer(p);
	}
	
	public void editPlayer(Player p) throws DatabaseConnexionException, UpdatePlayerException{
		//TODO ajouter verif probablement dans la classe elle-m�me
		model.updatePlayer(p);
	}
	
	public void deletePlayer(int id) throws DatabaseConnexionException, DeleteMultipleException{
		model.delete(id);
	}
	
	/*
	 * Fonction de sécurisation de la structure de données Player.
	 * Renvoie TRUE si les données sont bonnes et renvoie FALSE dans le cas contraire.
	 */
	public boolean security(Player player)
	{
		String erreur = "";
		//Test name
			if(player.getName().length() < 3)
			{
				erreur += "\nNom trop court.";
			}
			else if(player.getName().length() > 255)
			{
				erreur += "\nNom trop long.";
			}
		//Test email
			Pattern regexEmail = Pattern.compile(
			        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
			);
			if(regexEmail.matcher(player.getMail()).matches() == false)
			{
				erreur +="\nEmail invalide.";
			}
		//Test password
			if(player.getPassword().length() < 5)
			{
				erreur += "\nMot de passe trop court.";
			}
			else if(player.getPassword().length() > 255)
			{
				erreur += "\nMot de passe trop long.";
			}
		//Affichage de l'erreur ou non
			if(erreur.length() != 0)
			{
				JOptionPane.showMessageDialog(null, erreur, "Erreur", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else
			{
				return true;
			}	
	}
}

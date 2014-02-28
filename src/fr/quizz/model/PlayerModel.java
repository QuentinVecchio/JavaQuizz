package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.PlayerNotSaveException;

	/**
	 * 	 This class allow to manage the player table of the database
	 * @author Matthieu CLIN, Quentin VECCHIO
	 */
public class PlayerModel extends Model {

	public PlayerModel() {
		super("player","code_joueur");
		
	}

	
	/**
	 * The function checks if a Player is registered in the database
	 * @param name the name of the Player
	 * @param password its password
	 * @return an object Player if it is in the database, null otherwise
	 * @throws DatabaseConnexionException 
	 */
	public Player exist(String name, String password) throws DatabaseConnexionException
	{
		Connection connexion = getConnection();
		ResultSet res = null;
		String sql = "SELECT * FROM joueur WHERE nom_joueur=? AND passwd_joueur=?";
		Player player = null;
		PreparedStatement requete = null;
		try
		{
			requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			requete.setString(1, name);
			requete.setString(2, password);
			res = requete.executeQuery();
			if(res.next())
			{
				player = new Player(res.getInt("code_joueur"),res.getString("nom_joueur"),res.getString("passwd_joueur"),res.getString("mail_joueur"));
			}
		}
		catch(SQLException e)
		{
			System.err.println("Problème avec la requête exist : " + sql);
			e.printStackTrace();
		}finally{
			closeResultSet(res);
			closeStatement(requete);
			closeConnection(connexion);
		}
		return player;
	}

    /**
     * Function which allows to save a Question in database
     * @param question, the question to save
     * @throws DatabaseConnexionException 
     * @throws DeleteMultipleException 
     */
    public void deletePlayer(Player player) throws DatabaseConnexionException, DeleteMultipleException{

    	super.delete(player.getCode());
    }

    /**
     * This function saves a player in the database
     * @param player the player to save
     * @return the id of the player in the database
     * @throws DatabaseConnexionException if connection is lost
     * @throws PlayerNotSaveException if insert fails
     */
    public int savePlayer(Player player) throws DatabaseConnexionException, PlayerNotSaveException{

    	if(player.getCode() != -1) return -1;
    	
    	Connection connexion = getConnection();
        String sql = "INSERT INTO "+this.getTableName()+" (nom_joueur,mail_joueur, pass_joueur) VALUES (?,?,?)";
        PreparedStatement requete = null;
        ResultSet res = null;
        
        try
        {
            requete  = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            requete.setString(1,player.getName());
            requete.setString(2,player.getPassword());
            requete.setString(3,player.getMail());

            res = requete.getGeneratedKeys();
            if(res.next()){
            	return res.getInt(1);
            }else{
            	throw new PlayerNotSaveException("Impossible d'enregistrer l'utilisateur");
            }
        }
        catch(SQLException e)
        {
        	System.err.println("Probleme avec la requete : " + sql + " " + e);
        }finally{
            closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);        	
        }
        return -1;
    }
    
    
}

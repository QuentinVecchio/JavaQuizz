package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.PlayerNotSaveException;
import fr.quizz.exception.UpdateException;
import fr.quizz.main.Launcher;

	/**
	 * 	 This class allow to manage the player table of the database
	 * @author Matthieu CLIN, Quentin VECCHIO
	 */
public class PlayerModel extends Model {

	public PlayerModel() {
		super("joueur","code_joueur");
		
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
				player = new Player(res.getInt("code_joueur"),res.getString("nom_joueur"),res.getString("passwd_joueur"),res.getString("mail_joueur"),res.getInt("admin"));
			}
		}
		catch(SQLException e)
		{
			System.err.println("Problème avec la requète exist : " + sql);
			Launcher.printException(e);
		}finally{
			closeResultSet(res);
			closeStatement(requete);
			closeConnection(connexion);
		}
		return player;
	}

    /**
     * Call the function delete from the superclass (only for convenient)
     * @param question, the player to delete
     * @throws DatabaseConnexionException 
     * @throws DeleteMultipleException 
     */
    public void deletePlayer(Player player) throws DatabaseConnexionException, DeleteMultipleException{

    	if(player.getCode() == -1) return;
    	
    	Connection connexion = getConnection();
        
        PreparedStatement requete = null;
        try
        {
    		connexion.setAutoCommit(false);
    		String sql = "DELETE FROM joueur WHERE code_joueur = ? ";
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);    
            requete.setInt(1, player.getCode());
            
            int res = requete.executeUpdate();
            System.out.println("res 1er:"+res);
    		sql = "DELETE FROM quizz WHERE code_joueur = ? ";
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);    
            requete.setInt(1, player.getCode());
            res = requete.executeUpdate();
            System.out.println("res 2e:"+res);
            commit(connexion);
        }
        catch(SQLException e)
        {
    		rollback(connexion);
    		Launcher.printException(e);
        }finally{
            closeStatement(requete);
            closeConnection(connexion);	
        }
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
        String sql = "INSERT INTO "+this.getTableName()+" (nom_joueur,mail_joueur, passwd_joueur, admin) VALUES (?,?,?,?)";
        PreparedStatement requete = null;
        ResultSet res = null;
        
        try{
            requete  = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            requete.setString(1,player.getName());
            requete.setString(2,player.getMail());
            requete.setString(3,player.getPassword());
            requete.setInt(4, player.getAdmin());
            requete.executeUpdate();

            res = requete.getGeneratedKeys();
            if(res.next()){
            	return res.getInt(1);
            }else{
            	throw new PlayerNotSaveException("Impossible d'enregistrer l'utilisateur");
            }
            
        }catch(SQLException e){
        	Launcher.printException(e);
        	System.err.println("Probleme avec la requete : " + sql);
        }finally{
            closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);        	
        }
        return -1;
    }
    
    public void updatePlayer(Player player) throws DatabaseConnexionException, UpdateException{
    	if(player.getCode() == -1) return;
    	Connection connexion = getConnection();
        String sql = "UPDATE "+this.getTableName()+" SET nom_joueur = ?, mail_joueur=?, passwd_joueur=? WHERE "+this.getTableId()+"= ?";
        PreparedStatement requete = null;
        
        try{
            requete  = connexion.prepareStatement(sql);
            requete.setString(1,player.getName());
            requete.setString(2,player.getMail());
            requete.setString(3,player.getPassword());
            requete.setInt(4,player.getCode());
            
            final int res = requete.executeUpdate();
            if(res != 1){
            	throw new UpdateException("Il y a "+res+" ligne(s) modifiée(s)");
            }
      
        }catch(SQLException e){
        	Launcher.printException(e);
        	System.err.println("Probleme avec la requete : " + sql);
        }finally{
            closeStatement(requete);
            closeConnection(connexion);        	
        }
    }    
    
    
    
    
    /**
     * @return a ArrayList which contains all the Player 
     * @throws DatabaseConnexionException 
     */
    public ArrayList<Player> getAllPlayer() throws DatabaseConnexionException{
    	ArrayList<Player> list = new ArrayList<Player>();
    	
    	Connection connexion = super.getConnection();
        ResultSet res = null;
        String sql = "SELECT * FROM "+this.getTableName();
        PreparedStatement requete = null;
        try{
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
            res = requete.executeQuery();
            
            while(res.next()){
                    list.add(new Player(res.getInt("code_joueur"),res.getString("nom_joueur"),res.getString("passwd_joueur"),res.getString("mail_joueur"),res.getInt("admin")));
            }
            return list;
            
        }catch(SQLException e){
            System.err.println("Probleme avec la requete getAllQuestion : " + sql);
            Launcher.printException(e);
        }finally{
        	closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);	
        }
        return list;
    }
    
    
}

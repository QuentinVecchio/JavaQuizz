package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.quizz.core.Player;

public class PlayerModel extends Model {

	public PlayerModel() {
		super();
		
	}

	
	/**
	 * Function which check if a Player is registered in the database
	 * @param name the name of the Player
	 * @param password his password
	 * @return an object Player if it is in the database, null otherwise
	 */
	public Player exist(String name, String password)
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
			System.err.println("Problème avec la requête exist : " + sql + " " + e);
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
     */
    public void deletePlayer(Player player){

    	if(player.getCode() == -1) return;
    	
    	Connection connexion = super.getConnection();
        
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
                super.commit(connexion);
        }
        catch(SQLException e)
        {
        		super.rollback(connexion);
                System.err.println("Probleme avec lors de la transaction" + e);
        }
        super.closeStatement(requete);
        super.closeConnection(connexion);
    }

}

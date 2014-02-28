package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.quizz.core.Player;

public class PlayerModel extends Model {
	private Connection connexion;
	

	public PlayerModel() {
		super();
		
	}

	public Player exist(String name, String password)
	{
		connexion = super.getConnection();
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
		}
		super.closeResultSet(res);
		super.closeStatement(requete);
		super.closeConnection(connexion);
		return player;
	}
}

package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.quizz.core.Item;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.ItemNotSaveException;

public class ItemModel extends Model {

	public ItemModel() {
		super("item", null);
	}

    /**
     * This function saves a item in the database
     * @param item the item to save
     * @return the id of the player in the database
     * @throws DatabaseConnexionException if connection is lost
     * @throws ItemNotSaveException if insert fails
     */
    public int saveItem(Item item) throws DatabaseConnexionException, ItemNotSaveException{
    	
    	Connection connexion = getConnection();
        String sql = "INSERT INTO "+this.getTableName()+" (code_question,code_quizz, reponse_joueur) VALUES (?,?,?)";
        PreparedStatement requete = null;
        ResultSet res = null;
        
        try{
            requete  = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            requete.setInt(1,item.getCode_question());
            requete.setInt(2,item.getCode_quizz());
            requete.setString(3,item.getReponse_joueur());
 
            requete.executeUpdate();

            res = requete.getGeneratedKeys();
            if(res.next()){
            	return res.getInt(1);
            }else{
            	throw new ItemNotSaveException("Impossible d'enregistrer l'item");
            }
            
        }catch(SQLException e){
        	e.printStackTrace();
        	System.err.println("Probleme avec la requete : " + sql);
        }finally{
            closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);        	
        }
        return -1;
    }
	
}

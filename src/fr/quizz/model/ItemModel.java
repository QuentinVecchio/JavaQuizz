package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.quizz.core.Item;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.ItemNotSaveException;
import fr.quizz.main.Launcher;

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
    public void saveItem(Item item) throws DatabaseConnexionException, ItemNotSaveException{
    	
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

        }catch(SQLException e){
        	Launcher.printException(e);
        	System.err.println("Probleme avec la requete : " + sql);
        }finally{
            closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);        	
        }
    }
    
    /**
     * @return a ArrayList which contains all the Player 
     * @throws DatabaseConnexionException 
     */
    public ArrayList<Item> getAllItem() throws DatabaseConnexionException{
    	ArrayList<Item> list = new ArrayList<Item>();
    	
    	Connection connexion = super.getConnection();
        ResultSet res = null;
        String sql = "SELECT * FROM "+this.getTableName();
        PreparedStatement requete = null;
        try{
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
            res = requete.executeQuery();
            
            while(res.next()){
                    list.add(new Item(res.getInt("code_question"), res.getInt("code_quizz"), res.getString("reponse_joueur")));
            }
            return list;
            
        }catch(SQLException e){
            System.err.println("Probleme avec la requete getAllItem : " + sql);
            Launcher.printException(e);
        }finally{
        	closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);	
        }
        return list;
    }
	
}

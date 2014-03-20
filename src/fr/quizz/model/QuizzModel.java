package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.quizz.core.Quizz;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.QuizzNotSaveException;
import fr.quizz.main.Launcher;

public class QuizzModel extends Model {

	protected QuizzModel() {
		super("quizz", "code_quizz");
	}
	
    /**
     * This function saves a quizz in the database
     * @param quizz the quizz to save
     * @return the id of the quizz in the database
     * @throws DatabaseConnexionException if connection is lost
     * @throws QuizzNotSaveException if insert fails
     */
    public int saveQuizz(Quizz quizz) throws DatabaseConnexionException, QuizzNotSaveException{
    	if(quizz.getCode() != -1) return -1;
    	
    	Connection connexion = getConnection();
        String sql = "INSERT INTO "+this.getTableName()+" (date_quizz, nb_questions_quizz, code_joueur) VALUES (?,?,?)";
        PreparedStatement requete = null;
        ResultSet res = null;
        
        try{
            requete  = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            requete.setString(1,quizz.getDate().toString());
            requete.setInt(2,quizz.getNumberQuestions());
            requete.setInt(3,quizz.getCode_joueur());
            requete.executeUpdate();

            res = requete.getGeneratedKeys();
            if(res.next()){
            	return res.getInt(1);
            }else{
            	throw new QuizzNotSaveException("Impossible d'enregistrer le quizz");
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
}

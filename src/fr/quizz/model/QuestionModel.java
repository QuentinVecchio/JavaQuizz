package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.quizz.core.Question;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.QuestionNotSaveException;
import fr.quizz.exception.UpdateException;
import fr.quizz.main.Launcher;
	/**
	 * 	 This class allow to manage the question table of the database
	 * @author Matthieu CLIN, Quentin VECCHIO
	 */
public class QuestionModel extends Model {

	public QuestionModel() {
        super("question", "code_question");
    }

    /**
     * Return the Question object matches the id "code"
     * @param code , the ID of the question in the database
     * @return an object Question or null if it's not in the database
     * @throws DatabaseConnexionException 
     */
    public Question getQuestion(int code) throws DatabaseConnexionException
    {
		Connection connexion = getConnection();
        ResultSet res = null;
        String sql = "SELECT * FROM "+this.getTableName()+" WHERE code_question=?";
        Question question = null;
        PreparedStatement requete = null;
        try
        {
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            requete.setInt(1, code);

            res = requete.executeQuery();
            if(res.next())
            {
                    question = new Question(res.getInt("code_question"),res.getString("texte_question"),res.getString("reponse_joueur"));
            }
            
            return question;
        }
        catch(SQLException e){
        	Launcher.printException(e);
        }finally{
        	closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);
        }
        return question;
    }

    /**
     * Get all the questions where the pattern "pattern" is matched
     * @param pattern the pattern that must be respect by the question 
     * @return a ArrayList which contains all the Question 
     * @throws DatabaseConnexionException 
     */
    public ArrayList<Question> getAllQuestionByTexteQuestion(String pattern) throws DatabaseConnexionException{
    	ArrayList<Question> list = new ArrayList<Question>();
    	
    	Connection connexion = super.getConnection();
        ResultSet res = null;
        String sql = "SELECT * FROM "+this.getTableName()+" WHERE texte_question LIKE ?";
        PreparedStatement requete = null;
        try
        {
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            requete.setString(1,"%"+pattern+"%"); 
            res = requete.executeQuery();
            while(res.next())
            {
                list.add(new Question(res.getInt("code_question"),res.getString("texte_question"),res.getString("reponse_joueur")));
            }
            return list;
        }
        catch(SQLException e)
        {
            System.err.println("Probleme avec la requete getAllQuestion : " + sql);
            Launcher.printException(e);
        }finally{
        	closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);	
        }
        return list;
    }
    
    /**
     * 
     * @return all questions in the database
     * @throws DatabaseConnexionException
     */
    public ArrayList<Question> getAllQuestion() throws DatabaseConnexionException{
    	ArrayList<Question> list = new ArrayList<Question>();
    	
    	Connection connexion = super.getConnection();
        ResultSet res = null;
        String sql = "SELECT * FROM "+this.getTableName();
        PreparedStatement requete = null;
        try
        {
            requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            res = requete.executeQuery();
            while(res.next())
            {
                list.add(new Question(res.getInt("code_question"),res.getString("texte_question"),res.getString("reponse_joueur")));
            }
            return list;
        }
        catch(SQLException e)
        {
            System.err.println("Probleme avec la requete getAllQuestion : " + sql);
            Launcher.printException(e);
        }finally{
        	closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);	
        }
        return list;
    }    
    

    /**
     * Function which allows to save a Question in database
     * @param question, the question to save
     * @return the new id of the question or -1 otherwise
     * @throws DatabaseConnexionException 
     * @throws QuestionNotSaveException 
     */
    public int saveQuestion(Question question) throws DatabaseConnexionException, QuestionNotSaveException{

    	if(question.getCode() != -1) return -1;
    	
    	Connection connexion = getConnection();

        String sql = "INSERT INTO "+this.getTableName()+" (texte_question,reponse_joueur) VALUES (?,?)";
        PreparedStatement requete = null;
        ResultSet res = null;
        
        try
        {
            requete  = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            requete.setString(1,question.getText());
            requete.setString(2,question.getAnswer());                    
            requete.executeUpdate();
            
            res = requete.getGeneratedKeys();
            if(res.next()){
            	return res.getInt(1);
            }else{
            	throw new QuestionNotSaveException("Impossible d'enregistrer la question");
            }
        }
        catch(SQLException e)
        {
        	System.err.println("Probleme avec la requete : " + sql + " " + e);
        	Launcher.printException(e);
        }finally{
            closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);        	
        }
        return -1;
    }

    /**
     * Call the function delete from the superclass (only for convenient)
     * @param question, the question to delete
     * @throws DatabaseConnexionException
     * @throws DeleteMultipleException
     */
    public void deleteQuestion(Question question) throws DatabaseConnexionException, DeleteMultipleException{
    	super.delete(question.getCode());
    }
    
    
    public void updateQuestion(Question question) throws DatabaseConnexionException, UpdateException{
    	if(question.getCode() == -1) return;
    	Connection connexion = getConnection();
        String sql = "UPDATE "+this.getTableName()+" SET texte_question= ? , reponse_joueur = ? WHERE "+this.getTableId()+"= ?";
        PreparedStatement requete = null;
        
        try{
            requete  = connexion.prepareStatement(sql);
            requete.setString(1, question.getText());
            requete.setString(2, question.getAnswer());
            requete.setInt(3, question.getCode());
            
            final int res = requete.executeUpdate();
            if(res != 1){
            	throw new UpdateException("Il y a "+res+" ligne(s) modifi�e(s)");
            }
      
        }catch(SQLException e){
        	Launcher.printException(e);
        	System.err.println("Probleme avec la requete : " + sql);
        }finally{
            closeStatement(requete);
            closeConnection(connexion);        	
        }
    }     
    
    
}

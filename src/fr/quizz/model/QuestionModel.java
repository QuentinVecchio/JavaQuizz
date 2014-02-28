package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.quizz.core.Question;

public class QuestionModel extends Model {

	public QuestionModel() {
                super();

        }

    /**
     * Return the Question object matches the id "code"
     * @param code l'ID de la question en BDD
     * @return an object Question or null if it's not in the database
     */
    public Question getQuestion(int code)
    {
    		Connection connexion = super.getConnection();
            ResultSet res = null;
            String sql = "SELECT * FROM question WHERE code_question=?";
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
            }
            catch(SQLException e)
            {
                    System.err.println("Probleme avec la requete exist : " + sql + " " + e);
            }
            super.closeResultSet(res);
            super.closeStatement(requete);
            super.closeConnection(connexion);
            return question;
    }

    /**
     * Get the number of entries in the question table
     * @return the number of question in the database
     */
    public int getCount()
    {
    		Connection connexion = super.getConnection();
            ResultSet res = null;
            String sql = "SELECT count(*) FROM question";
            PreparedStatement requete = null;
            try
            {
                    requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

                    res = requete.executeQuery();
                    if(res.next())
                    {
                            return res.getInt(1);
                    }
            }
            catch(SQLException e)
            {
                    System.err.println("Probleme avec la requete exist : " + sql + " " + e);
            }
            super.closeResultSet(res);
            super.closeStatement(requete);
            super.closeConnection(connexion);
            return -1;
    }

    /**
     * Get all the questions where the pattern "pattern" is matched
     * @param pattern the pattern that must be respect by the question 
     * @return a ArrayList which contains all the Question 
     */
    public ArrayList<Question> getAllQuestion(String pattern){
    	ArrayList<Question> list = new ArrayList<Question>();
    	
    	Connection connexion = super.getConnection();
        ResultSet res = null;
        String sql = "SELECT * FROM question WHERE texte_question LIKE ?";
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
                System.err.println("Probleme avec la requete getAllQuestion : " + sql + " " + e);
        }
        super.closeResultSet(res);
        super.closeStatement(requete);
        super.closeConnection(connexion);
    	return list;
    }

    /**
     * Function which allows to save a Question in database
     * @param question, the question to save
     */
    public int saveQuestion(Question question){

    	if(question.getCode() != -1) return -1;
    	
    	Connection connexion = super.getConnection();
        String sql = "INSERT INTO question (texte_question,reponse_joueur) VALUES (?,?)";
        PreparedStatement requete = null;
        ResultSet res = null;
        
        try
        {
                requete  = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                requete.setString(1,question.getText());
                requete.setString(2,question.getAnswer());                    
                
                res = requete.getGeneratedKeys();
                if(res.next()){
                	return res.getInt(1);
                }
        }
        catch(SQLException e)
        {
                System.err.println("Probleme avec la requete : " + sql + " " + e);
        }
        super.closeResultSet(res);
        super.closeStatement(requete);
        super.closeConnection(connexion);
        
        return -1;
    }
        
}

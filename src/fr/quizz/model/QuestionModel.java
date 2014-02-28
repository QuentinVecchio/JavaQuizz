package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.quizz.core.Question;

public class QuestionModel extends Model {
        private Connection connexion;


        public QuestionModel() {
                super();

        }

        public Question getQuestion(int code)
        {
                connexion = super.getConnection();
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

        public int getCount()
        {
                connexion = super.getConnection();
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

        public ArrayList<Question> getAllQuestion(String pattern){
        	ArrayList<Question> list = new ArrayList<Question>();
        	
            connexion = super.getConnection();
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
        	return null;
        }

        public void saveQuestion(Question question){

        	if(question.getCode() != -1) return;
        	
            connexion = super.getConnection();
            ResultSet res = null;
            String sql = "INSERT INTO question (texte_question,reponse_joueur) VALUES (?,?)";
            PreparedStatement requete = null;
            try
            {
                    requete  = connexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    requete.setString(1,question.getText());
                    requete.setString(2,question.getAnswer());                    
                    res = requete.executeQuery();
                    System.out.println("ou");
                    if(res.next()){
                    	System.out.println(res.toString());
                    }else{
                    	System.out.println("bizarre");
                    }
            }
            catch(SQLException e)
            {
                    System.err.println("Probleme avec la requete : " + sql + " " + e);
            }
            super.closeResultSet(res);
            super.closeStatement(requete);
            super.closeConnection(connexion);
        }
        
}

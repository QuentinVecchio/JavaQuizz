package fr.quizz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        System.err.println("Problème avec la requête exist : " + sql + " " + e);
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
                        System.err.println("Problème avec la requête exist : " + sql + " " + e);
                }
                super.closeResultSet(res);
                super.closeStatement(requete);
                super.closeConnection(connexion);
                return -1;
        }
}

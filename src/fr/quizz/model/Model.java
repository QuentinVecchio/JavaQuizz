package fr.quizz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.quizz.exception.DatabaseConnexionException;
	/**
	 * 	 
	 * @author Matthieu CLIN, Quentin VECCHIO
	 */
public class Model {
	
	public final static String[] BDD_IUT = {"jdbc:mysql://infodb2.iutmetz.ad.univ-lorraine.fr/clin1u_tp_quizz",
											"clin1u_appli",
											"31202702"
											};
	
	public final static String[] BDD_QUENTIN = {"jdbc:mysql://localhost:8889/quizz_java",
												 "root",
												 "root"
												};

	public final static String[] BDD_MATTHIEU = {"jdbc:mysql://localhost/quizz_java",
												 "root",
												 ""
												};
	
	final private static String BDD_DRIVER = "com.mysql.jdbc.Driver";
	
	private static String BDD_URL;
	
	private static String BDD_USER;
	
	private static String BDD_PASSWORD;

	private static Connection connection = null;
	
	private String tableName;
	
	/**
	 * 
	 * @param tableName the name of the table in the database
	 */
	protected Model(String tableName){
		this.tableName = tableName;
		BDD_URL = BDD_IUT[0];
		BDD_USER = BDD_IUT[1];
		BDD_PASSWORD = BDD_IUT[2];
	}
	
    /**
     * Get the number of entries in the table tableName
     * @return the number of entries in the database
     * @throws DatabaseConnexionException 
     */
	public int getCount() throws DatabaseConnexionException
    {
		Connection connexion = getConnection();
        ResultSet res = null;
        String sql = "SELECT count(*) FROM "+this.tableName;
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
                System.err.println("Probleme avec la requete : " + sql + " " + e);
        }finally{
            closeResultSet(res);
            closeStatement(requete);
            closeConnection(connexion);
        }

        return -1;
    }
	
	
    /**
     * Close the Connection to the database
     * @return a working connection to the database
     * @throws DatabaseConnexionException 
     */
	public static Connection getConnection() throws DatabaseConnexionException{
		if (connection == null) {
			try {
				Class.forName(BDD_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new DatabaseConnexionException("Erreur de connexion à la base de donnée");
			}
			
			try {
				connection = DriverManager.getConnection(BDD_URL, BDD_USER, BDD_PASSWORD);
			} catch (SQLException e) {
				closeConnection(connection);
				e.printStackTrace();
				throw new DatabaseConnexionException("Erreur de connexion à la base de donnée");
			}
		}
		
		return connection;
	}
	
	/**
	 * Close the ResultSet
	 * @param res, the ResultSet to close
	 */
	public static void closeResultSet(ResultSet res){
		if(res != null){
			try {
				if(!res.isClosed()){
					res.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * Close the Statement
	 * @param s, the Statement to close
	 */
	public static void closeStatement(Statement s){
		if(s != null){
			try {
				if(s.isClosed()){
					s.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	/**
	 * 
	 * @param c, the connection to close;
	 */
	public static void closeConnection(Connection c){
		if(c != null){
			try {
				if(!c.isClosed()){
					c.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Rollback the current transaction
	 * @param c, the connection to rollback
	 */
	public static void rollback(Connection c){
		if(c!= null){
			try {
			if(!c.isClosed()){
				if(!c.getAutoCommit()){
					c.rollback();
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Commit the current transaction
	 * @param c, the connection to commit
	 */
	public static void commit(Connection c){
		if(c!= null){
			try {
			if(!c.isClosed()){
				if(!c.getAutoCommit()){
					c.commit();
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

package fr.quizz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
	
	public final static String[] BDD_IUT = {"jdbc:mysql://infodb2.iutmetz.ad.univ-lorraine.fr/clin1u_tp_quizz",
											"clin1u_appli",
											"31202702"
											};
	
	public final static String[] BDD_QUENTIN = {"jdbc:mysql://localhost:80/quizz_java",
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
	
	protected Model(){
		BDD_URL = BDD_QUENTIN[0];
		BDD_USER = BDD_QUENTIN[1];
		BDD_PASSWORD = BDD_QUENTIN[2];
	}
	
	public static Connection getConnection(){
		if (connection!=null) {
			try {
				Class.forName(BDD_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				connection = DriverManager.getConnection(BDD_URL, BDD_USER, BDD_PASSWORD);
			} catch (SQLException e) {
				closeConnection(connection);
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	public static void closeResultSet(ResultSet res){
		if(res != null){
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public static void closeStatement(Statement s){
		if(s != null){
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	public static void closeConnection(Connection c){
		if(c != null){
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection c){
		if(c!= null){
			try {
			if(!c.isClosed()){
				if(!c.getAutoCommit()){
					System.out.println("Je rollback");
					c.rollback();
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void commit(Connection c){
		if(c!= null){
			try {
			if(!c.isClosed()){
				if(!c.getAutoCommit()){
					c.commit();
					System.out.println("Je ferme");
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

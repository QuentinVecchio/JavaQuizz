package fr.quizz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
	
	public final static String[] BDD_IUT = {"jdbc:mysql://infodb2.iutmetz.ad.univ-lorraine.fr/clin1u_bourse_livre",
											"clin1u_appli",
											"31202702"
											};
	
	public final static String[] BDD_QUENTIN = {"jdbc:mysql://localhost/clin1u_bourse_livre",
												 "root",
												 "root"
												};

	public final static String[] BDD_MATTHIEU = {"jdbc:mysql://localhost/clin1u_bourse_livre",
												 "root",
												 ""
												};
	
	final private static String BDD_DRIVER = "com.mysql.jdbc.Driver";
	
	private String BDD_URL;
	
	private String BDD_USER;
	
	private String BDD_PASSWORD;
	
	public Model(){
		BDD_URL = BDD_IUT[0];
		BDD_USER = BDD_IUT[1];
		BDD_PASSWORD = BDD_IUT[2];
	}
	
	public Connection getConnection(){
		Connection connection = null;
		
		try {
			Class.forName(BDD_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(BDD_URL, BDD_USER, BDD_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeResultSet(ResultSet res){
		try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package fr.quizz.main;

import java.sql.Connection;

import fr.quizz.model.Model;

public class Launcher {

	/**
	 * @author Matthieu CLIN, Quentin Vecchio
	 * @param args
	 */
	public static void main(String[] args) {
		Connection c = new Model().getConnection();
	}

}

package fr.quizz.controller;

import javax.swing.JOptionPane;

import fr.quizz.core.Question;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.QuestionNotSaveException;
import fr.quizz.exception.UpdateException;
import fr.quizz.model.QuestionModel;

public class QuestionController extends Controller {

	QuestionModel model = new QuestionModel();
	
	public QuestionController() {
		// TODO Auto-generated constructor stub
	}

	public int addQuestion(Question q) throws DatabaseConnexionException, QuestionNotSaveException{
		//TODO ajouter vérification sur les valeurs du question
		return model.saveQuestion(q);
	}
	
	public void editQuestion(Question q) throws DatabaseConnexionException, UpdateException{
		model.updateQuestion(q);
	}
	
	public void deleteQuestion(int id) throws DatabaseConnexionException, DeleteMultipleException{
		model.delete(id);
	}
	
	public boolean security(Question question)
	{
		String erreur = "";
		//Test question
			if(question.getText().length() > 1000)
			{
				erreur += "\nQuestion trop longue.";
			}
		//Test reponse
			if(question.getAnswer().length() > 255)
			{
				erreur += "\nRéponse trop longue.";
			}
		//Affichage de l'erreur ou non
			if(erreur.length() != 0)
			{
				JOptionPane.showMessageDialog(null, erreur, "Erreur", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else
			{
				return true;
			}	
	}
}

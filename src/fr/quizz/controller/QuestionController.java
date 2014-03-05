package fr.quizz.controller;

import fr.quizz.core.Question;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.QuestionNotSaveException;
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
	
	public void editQuestion(Question q) throws DatabaseConnexionException{
		//TODO ajouter verif probablement dans la classe elle-même
		//
	}
	
	public void deleteQuestion(int id) throws DatabaseConnexionException, DeleteMultipleException{
		model.delete(id);
	}
}

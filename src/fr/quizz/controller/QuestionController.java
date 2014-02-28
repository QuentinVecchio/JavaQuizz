package fr.quizz.controller;

import fr.quizz.core.Player;
import fr.quizz.view.question.Dashboard;

public class QuestionController extends Controller {

	public QuestionController() {
		// TODO Auto-generated constructor stub
	}
	
	public void dashboard(Player p){
		Dashboard d = new Dashboard(this, p);
		d.showFrame();
	}

}

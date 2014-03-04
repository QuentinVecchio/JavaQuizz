package fr.quizz.controller;

import javax.swing.JPanel;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.model.PlayerModel;
import fr.quizz.model.QuestionModel;
import fr.quizz.view.dashboard.Dashboard;
import fr.quizz.view.player.Edit;
import fr.quizz.view.player.ManagePlayer;
import fr.quizz.view.question.ManageQuestion;


public class DashboardController extends Controller {
	public void dashboard(Player p){
		Dashboard d = new Dashboard(this, p);
		d.showFrame();
	}
	
	public JPanel showEditPlayerPanel(Player p)
	{
		Edit panelEditPlayer = new Edit(p);
		return panelEditPlayer;
	}
	
	public JPanel showManagerPlayerPanel() throws DatabaseConnexionException{
		PlayerModel pM = new PlayerModel();
		ManagePlayer panelManagePlayer = new ManagePlayer(pM.getAllPlayer());
		return panelManagePlayer;
	}
	
	public JPanel showManagerQuestionPanel() throws DatabaseConnexionException{
		QuestionModel pM = new QuestionModel();
		ManageQuestion panelManageQuestion = new ManageQuestion(pM.getAllQuestion());
		return panelManageQuestion;
	}
}

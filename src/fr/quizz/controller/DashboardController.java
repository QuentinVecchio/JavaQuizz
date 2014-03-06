package fr.quizz.controller;

import javax.swing.JPanel;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.model.PlayerModel;
import fr.quizz.model.QuestionModel;
import fr.quizz.view.Init;
import fr.quizz.view.dashboard.Dashboard;
import fr.quizz.view.player.ManagePlayer;
import fr.quizz.view.question.ManageQuestion;
import fr.quizz.view.player.Profil;;

public class DashboardController extends Controller {
	public void dashboard(Player p){
		Dashboard d = new Dashboard(this, p);
		d.showFrame();
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
	
	public JPanel showProfilPanel(Player p) throws DatabaseConnexionException{
		Profil profil = new Profil(p);
		return profil;
	}
	
	public void showQuizzDialog() throws DatabaseConnexionException
	{
		Init init = new Init();
	}
}

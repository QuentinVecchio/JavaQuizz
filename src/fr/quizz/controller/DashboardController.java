package fr.quizz.controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.model.PlayerModel;
import fr.quizz.model.QuestionModel;
import fr.quizz.view.Index;
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
	
	public JPanel showQuizz(int nbQuestion, String keyWord) throws DatabaseConnexionException{
		if(nbQuestion <= 0 || keyWord == "")
		{
			String erreur = "";
			if(nbQuestion <= 0)
				erreur = "Nombre de question non valide.\n";
			else
				erreur = "Mot clé non valide.";
			JOptionPane.showMessageDialog(null, erreur, "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
		{
			/*
			 * Passer à la vue pQ
			 * Un array list de quizz
			 */
			
			Index pQ = new Index();
			return pQ;
		}	
	}
}

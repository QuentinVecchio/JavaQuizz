package fr.quizz.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.quizz.core.Player;
import fr.quizz.core.Question;
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
	
	public JPanel showQuizz(String NbQuestion, String keyWord,Player p) throws DatabaseConnexionException{
		if(NbQuestion.trim().length() == 0 || keyWord.trim().length() == 0)
		{
			int nb = Integer.parseInt(NbQuestion);
			String erreur = "";
			if(NbQuestion.trim().length() == 0 || nb <= 0)
				erreur = "Nombre de question non valide.\n";
			else
				erreur = "Mot clé non valide.";
			JOptionPane.showMessageDialog(null, erreur, "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
		{
			int nb = Integer.parseInt(NbQuestion);
			final QuestionModel questionModel = new QuestionModel();
			ArrayList<Question> listQuestion =  questionModel.getAllQuestionByTexteQuestion(keyWord);
			if(listQuestion.size() == 0){
				JOptionPane.showMessageDialog(null, "Aucune question trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
				return null;
			}else{

				final Random s = new Random();
				int maxElt = (nb < listQuestion.size()) ?nb: listQuestion.size();
				final int max = maxElt;
				ArrayList<Integer> selectIndex = new ArrayList<Integer>();
				ArrayList<Question> selectedQuestion = new ArrayList<Question>();

				
				while(maxElt > 0){
					int current;
					do{
						current = s.nextInt(max);

					}while(current < 0);
					
					if(!selectIndex.contains(current)){
						selectIndex.add(current);
						selectedQuestion.add(listQuestion.get(current));
						maxElt --;
					}
				}
				Index pQ = new Index(selectedQuestion,p);
				return pQ;
			}
		}
	}
}

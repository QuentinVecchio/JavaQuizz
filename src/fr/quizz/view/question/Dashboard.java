package fr.quizz.view.question;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import fr.quizz.controller.QuestionController;
import fr.quizz.core.Player;

public class Dashboard extends JFrame{

	private static final long serialVersionUID = 5273176322965681458L;

	private QuestionController controller;
	
	private Player player;
	
	public Dashboard(QuestionController qC,Player p){
		this.player = p;
		this.controller = qC;
		setMenuBar(initMenu());
		initComponents();

	}
	
	public void showFrame(){
		setVisible(true);
	}
	
	public void initComponents(){
		setTitle("Tableau de bord: "+player.getName());
		setSize(new Dimension(300,200));
		setLocationRelativeTo(null);

	}
	
	public MenuBar initMenu(){
		MenuBar menu = new MenuBar();
		
		Menu m_fichier = new Menu("Fichier");
			MenuItem fichier_quitter = new MenuItem("Quitter");
			m_fichier.add(fichier_quitter);
		menu.add(m_fichier);
		Menu m_question = new Menu("Question");
			MenuItem question_ajouter = new MenuItem("Ajouter");
			question_ajouter.addActionListener(new ActionAddQuestion());
			m_question.add(question_ajouter);
		menu.add(m_question);	
		return menu;
	}
	
	
	class ActionAddQuestion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	

	public QuestionController getController() {
		return controller;
	}

	public void setController(QuestionController controller) {
		this.controller = controller;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}

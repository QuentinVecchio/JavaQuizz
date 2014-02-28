package fr.quizz.view.dashboard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.quizz.controller.DashboardController;
import fr.quizz.core.Player;

public class Dashboard extends JFrame{

	//Variables d'instances 
	private static final long serialVersionUID = 1L;
	private DashboardController controller;
	private JPanel panelButton = new JPanel();
	private JPanel panelMain = new JPanel();
	private JButton btnAddGamer = new JButton("Nouveau joueur");
	private JButton btnEditGamer = new JButton("Modification joueur");
	private JButton btnDelGamer = new JButton("Suppression joueur");
	private JButton btnAddQuestion = new JButton("Nouvelle Question");
	private JButton btnEditQuestion = new JButton("Modification Question");
	private JButton btnDelQuestion = new JButton("Suppression Question");
	private JButton btnQuizz = new JButton("Quizz");
	private JButton btnUser = new JButton("Profil");
	private JButton btnQuit = new JButton("Quitter");
	private Player p;
	
	public Dashboard(DashboardController dC,Player p) 
	{
		super();
		//Définition du controller
		this.controller = dC;
		//Définition du joueur
		this.p = p;
		//Définition de la fenetre
	    this.setSize(800, 400);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    //Définition des éléments internes de la fenetre
	    this.getContentPane().add(panelButton, BorderLayout.WEST);
	    this.getContentPane().add(panelMain, BorderLayout.CENTER);
	    //Appel constructeur Panel
	    this.PanelButtonConstructor();
	}
	
	public void showFrame(){
		setVisible(true);
	}
	
	public void setViewCenter(JPanel newView)
	{
		panelMain.removeAll();
		panelMain.add(newView);
	}
	
	private void PanelButtonConstructor()
	{
		panelButton.setLayout(new GridLayout(10,1));
		//Affichage du nom
			JLabel hello = new JLabel(" Bonjour " + this.p.getName(),SwingConstants.CENTER);
			panelButton.add(hello);
		//Construction Panel Admin
			this.PanelAdmin();
		//Construction Panel Gamer
			this.PanelGamer();
	}
	
	private void PanelAdmin()
	{
		//Construction bouton add Gamer
			panelButton.add(btnAddGamer);
			btnAddGamer.addActionListener(new ActionAddGamer());
		//Construction bouton edit Gamer
			panelButton.add(btnEditGamer);
			btnAddGamer.addActionListener(new ActionEditGamer());
		//Construction bouton delete Gamer
			panelButton.add(btnDelGamer);
			btnAddGamer.addActionListener(new ActionDelGamer());
		//Construction bouton add Question
			panelButton.add(btnAddQuestion);
			btnAddGamer.addActionListener(new ActionAddQuestion());
		//Construction bouton edit Question
			panelButton.add(btnEditQuestion);
			btnAddGamer.addActionListener(new ActionEditQuestion());
		//Construction bouton delete Question
			panelButton.add(btnDelQuestion);
			btnAddGamer.addActionListener(new ActionDelQuestion());
	}
	
	private void PanelGamer()
	{
		//Construction bouton Quizz
			panelButton.add(btnQuizz);
			btnQuizz.addActionListener(new ActionQuizz());
		//Construction bouton Profil
			panelButton.add(btnUser);
			btnUser.addActionListener(new ActionUser());
		//Construction bouton Quitter
			panelButton.add(btnQuit);
			btnQuit.addActionListener(new ActionQuit());
	}
	
	class ActionAddGamer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionEditGamer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionDelGamer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionAddQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionEditQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionDelQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionQuizz implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionUser implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionQuit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			//TODO fermer la BDD !!!
		}
	}
}

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
	private JButton btnAddPlayer = new JButton("Nouveau joueur");
	private JButton btnEditPlayer = new JButton("Modification joueur");
	private JButton btnDelPlayer = new JButton("Suppression joueur");
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
	    //Appel constructeur Panel
	    this.panelButtonConstructor();
	}
	
	public void showFrame(){
		setVisible(true);
	}
	
	public void setViewCenter(JPanel newView)
	{
		this.getContentPane().add(newView, BorderLayout.CENTER);
		this.validate();
	}
	
	private void panelButtonConstructor()
	{
		panelButton.setLayout(new GridLayout(10,1));
		//Affichage du nom
			JLabel hello = new JLabel(" Bonjour " + this.p.getName(),SwingConstants.CENTER);
			panelButton.add(hello);
		//Construction Panel Admin
			this.panelAdmin();
		//Construction Panel Gamer
			this.panelPlayer();
	}
	
	private void panelAdmin()
	{
		//Construction bouton add Gamer
			panelButton.add(btnAddPlayer);
			btnAddPlayer.addActionListener(new ActionAddGamer());
		//Construction bouton edit Gamer
			panelButton.add(btnEditPlayer);
			btnAddPlayer.addActionListener(new ActionEditGamer());
		//Construction bouton delete Gamer
			panelButton.add(btnDelPlayer);
			btnAddPlayer.addActionListener(new ActionDelGamer());
		//Construction bouton add Question
			panelButton.add(btnAddQuestion);
			btnAddPlayer.addActionListener(new ActionAddQuestion());
		//Construction bouton edit Question
			panelButton.add(btnEditQuestion);
			btnAddPlayer.addActionListener(new ActionEditQuestion());
		//Construction bouton delete Question
			panelButton.add(btnDelQuestion);
			btnAddPlayer.addActionListener(new ActionDelQuestion());
	}
	
	private void panelPlayer()
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
			setViewCenter(controller.showAddPlayerPanel());
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

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
	private JButton btnManagePlayer = new JButton("Gestion Joueurs");
	private JButton btnAddQuestion = new JButton("Nouvelle Question");
	private JButton btnManageQuestion = new JButton("Gestion Questions");
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
		panelButton.setLayout(new GridLayout(9,1));
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
		//Construction bouton manage Gamer
			panelButton.add(btnManagePlayer);
			btnManagePlayer.addActionListener(new ActionManagePlayer());
		//Construction bouton add Question
			panelButton.add(btnAddQuestion);
			btnAddPlayer.addActionListener(new ActionAddQuestion());
		//Construction bouton edit Question
			panelButton.add(btnManageQuestion);
			btnAddPlayer.addActionListener(new ActionManageQuestion());
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
	
	class ActionManagePlayer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionAddQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionManageQuestion implements ActionListener{
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

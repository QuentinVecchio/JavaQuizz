package fr.quizz.view.dashboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.quizz.controller.DashboardController;
import fr.quizz.core.Player;
import fr.quizz.core.Sound;
import fr.quizz.exception.DatabaseConnexionException;

public class Dashboard extends JFrame{

	//Variables d'instances 
	private static final long serialVersionUID = 1L;
	private DashboardController controller;
	private JPanel panelButton = new JPanel();
	private JButton btnManagePlayer = new JButton("Gestion Joueurs");
	private JButton btnManageQuestion = new JButton("Gestion Questions");
	private JButton btnQuizz = new JButton("Quizz");
	private JButton btnUser = new JButton("Profil");
	private JButton btnQuit = new JButton("Quitter");
	private Player p;
	
	public Dashboard(DashboardController dC,Player p) 
	{
		super();
		//JOKE
		if(p.getName().length() > 40)
		{
			Sound s = new Sound();
			s.run("joke.wav");
		}
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
		this.getContentPane().removeAll();
		this.getContentPane().add(panelButton, BorderLayout.WEST);
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
			if(p.getAdmin() == 1)
				this.panelAdmin();
		//Construction Panel Gamer
			this.panelPlayer();
	}
	
	private void panelAdmin()
	{
		//Construction bouton manage Gamer
			panelButton.add(btnManagePlayer);
			btnManagePlayer.addActionListener(new ActionManagePlayer());
		//Construction bouton edit Question
			panelButton.add(btnManageQuestion);
			btnManageQuestion.addActionListener(new ActionManageQuestion());
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
	
	class ActionManagePlayer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				setViewCenter(controller.showManagerPlayerPanel());
			} catch (DatabaseConnexionException e1) {
				JOptionPane.showMessageDialog(null, "La connexion a la base de donnees n'a pas pu être effectuée !");
			}
		}
	}
	
	class ActionAddQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionManageQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				setViewCenter(controller.showManagerQuestionPanel());
			} catch (DatabaseConnexionException e1) {
				JOptionPane.showMessageDialog(null, "La connexion a la base de donnees n'a pas pu être effectuée !");
			}
		}
	}
	
	class ActionQuizz implements ActionListener{
		public void actionPerformed(ActionEvent e) {
				Init dialog = new Init();
		}
	}
	
	class ActionUser implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				setViewCenter(controller.showProfilPanel(p));
			} catch (DatabaseConnexionException e1) {
				JOptionPane.showMessageDialog(null, "La connexion a la base de donnees n'a pas pu être effectuée !");
			}
		}
	}
	
	class ActionQuit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			//TODO fermer la BDD !!!
		}
	}
	
	class Init extends JDialog {
		private static final long serialVersionUID = 1L;
		private JButton btnValide = new JButton("Valider");
		private JTextField textKeyWord = new JTextField();
		private JTextField nbQuestion = new JTextField();
		private JPanel panelKeyWord = new JPanel();
		private JPanel panelNbQuestion = new JPanel();
		private JPanel panelButtons = new JPanel();
		
		public Init()
		{
			super();
			setTitle("Paramétrage Quizz:");
			setSize(new Dimension(250,290));
			setLocationRelativeTo(null);
			setModal(true);
			//Panel Question
				panelKeyWord.add(new JLabel("Mot-clé : "));
				panelKeyWord.add(textKeyWord);
				textKeyWord.setPreferredSize(new Dimension(200,30));
			//Panel Answer
				panelNbQuestion.add(new JLabel("Nombre de questions : "));
				panelNbQuestion.add(nbQuestion);
				nbQuestion.setPreferredSize(new Dimension(200,30));
			//Panel Button
				panelButtons.add(btnValide);
				btnValide.addActionListener(new ActionValide());
			//Panel Main
				this.setLayout(new GridLayout(3,1));
				this.add(panelKeyWord);
				this.add(panelNbQuestion);
				this.add(panelButtons);
			this.setVisible(true);
		}
		
		class ActionValide implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					setViewCenter(controller.showQuizz(nbQuestion.getText(),textKeyWord.getText(),p));
					setVisible(false);
				} catch (DatabaseConnexionException e1) {
					JOptionPane.showMessageDialog(null, "La connexion a la base de donnees n'a pas pu être effectuée !");
				}				
			}
		}
	}
}

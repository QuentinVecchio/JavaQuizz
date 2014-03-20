package fr.quizz.view.player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.quizz.controller.DashboardController;
import fr.quizz.controller.PlayerController;
import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;

public class Login extends JDialog {

	private static final long serialVersionUID = 8962180489176367303L;

	private JTextField m_login = new JTextField();

	private JPasswordField m_password = new JPasswordField();
	
	private JButton m_logButton = new JButton("Connexion");
	
	private PlayerController controller;
	
	public Login(PlayerController pC) {
		controller = pC;
		initComponents();
	}
	
	public void initComponents(){
		setTitle("Connexion");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(300,200));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JPanel panelLogin = new JPanel();
		JPanel panelMDP = new JPanel();
		JPanel panelMain = new JPanel();
		
		panelMain.setLayout(new GridLayout(2,1));
		panelMain.add(panelLogin);
		panelMain.add(panelMDP);
		
		
		
		panelLogin.add(new JLabel("Login:"));
		panelLogin.add(m_login);
		panelMDP.add(new JLabel("Mot de passe:"));
		panelMDP.add(m_password);
		
		m_login.setPreferredSize(new Dimension(250,30));
		m_password.setPreferredSize(new Dimension(250,30));
		
		add(panelMain, BorderLayout.CENTER);
		m_logButton.addActionListener(new ActionLogin());
		add(m_logButton, BorderLayout.SOUTH);
	}
	
	public void showDialog(){
		setVisible(true);
	}
	
	class ActionLogin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			final String login = m_login.getText();
			final String password = m_password.getText();
			if(login.length() > 0 && password.length() > 0){
				Player p = null;
				try {
					p = controller.playerExists(login, password);
					if(p == null){
						JOptionPane.showMessageDialog(null, "Joueur non trouve dans la base de données");
					}else{
						DashboardController dC = new DashboardController();
						setVisible(false);
						dC.dashboard(p);
					}	
				} catch (DatabaseConnexionException e1) {
					JOptionPane.showMessageDialog(null, "La connexion a la base de donnees n'a pas pu être effectuée !");
				}
						
			}else{
				JOptionPane.showMessageDialog(null, "Erreur un ou plusieurs champs vide(s)");
			}
		}
	}
}

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
import javax.swing.JTextField;

import fr.quizz.controller.PlayerController;
import fr.quizz.controller.QuestionController;
import fr.quizz.core.Player;

public class Login extends JDialog {

	private static final long serialVersionUID = 8962180489176367303L;

	private JTextField m_login = new JTextField();

	private JTextField m_password = new JTextField();
	
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

		GridLayout d = new GridLayout(2,2);
		d.setVgap(10);
		JPanel p_loginForm = new JPanel(d);

		m_login.setSize(new Dimension(100,20));
		
		p_loginForm.add(new JLabel("Login:"));
		p_loginForm.add(m_login);
		p_loginForm.add(new JLabel("Mot de passe:"));
		p_loginForm.add(m_password);
		
		add(p_loginForm, BorderLayout.CENTER);
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
				final Player p = controller.playerExists(login, password);
				if(p == null){
					JOptionPane.showMessageDialog(null, "Joueur non trouve dans la base de donnees");
				}else{
					QuestionController qC = new QuestionController();
					setVisible(false);
					qC.dashboard(p);
				}			
			}else{
				JOptionPane.showMessageDialog(null, "Erreur un ou plusieurs champs vide(s)");
			}
		}
	}
}

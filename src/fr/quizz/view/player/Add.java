package fr.quizz.view.player;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.quizz.controller.PlayerController;
import fr.quizz.core.Player;

public class Add extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player p;
	private JButton btnValide = new JButton("Valider");
	private JButton btnReset = new JButton("RAZ");
	private JTextField textName = new JTextField();
	private JTextField textMail = new JTextField();
	private JPasswordField textPassword = new JPasswordField();
	private JPanel panelName = new JPanel();
	private JPanel panelMail = new JPanel();
	private JPanel panelPassword = new JPanel();
	private JPanel panelButton = new JPanel();
	
	public Add() {
		super();
		//Panel Name
			panelName.add(new JLabel("Login : "));
			panelName.add(textName);
			textName.setPreferredSize(new Dimension(200,30));
		//Panel Mail
			panelMail.add(new JLabel("Email : "));
			panelMail.add(textMail);
			textMail.setPreferredSize(new Dimension(200,30));
		//Panel Password
			panelPassword.add(new JLabel("Mot de passe : "));
			panelPassword.add(textPassword);
			textPassword.setPreferredSize(new Dimension(200,30));
		//Panel Button
			panelButton.add(btnReset);
			panelButton.add(btnValide);
			btnReset.addActionListener(new ActionReset());
			btnValide.addActionListener(new ActionValide());
		//Panel Main
			this.setLayout(new GridLayout(4,1));
			this.add(panelName);
			this.add(panelMail);
			this.add(panelPassword);
			this.add(panelButton);
			//this.setVisible(true);
	}
	
	class ActionValide implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			PlayerController pC = new PlayerController();
			p = new Player(-1, textName.toString(), textPassword.toString(), textMail.toString());
			if(pC.addPlayer(p))
			{
				JOptionPane.showMessageDialog(null, "L'utilisateur a été créé", "Information", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "L'utilisateur n'a pas été créé", "Information", JOptionPane.ERROR_MESSAGE);
				textName.setText("");
				textMail.setText("");
				textPassword.setText("");
			}
		}
	}
	
	class ActionReset implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			textName.setText("");
			textMail.setText("");
			textPassword.setText("");
		}
	}
	
}

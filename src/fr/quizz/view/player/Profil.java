package fr.quizz.view.player;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.quizz.controller.PlayerController;
import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.UpdatePlayerException;

public class Profil extends JPanel {
	private static final long serialVersionUID = 1L;
	private Player player;
	private JButton btnValide = new JButton("Valider");
	private JButton btnReset = new JButton("RAZ");
	private JTextField textName = new JTextField();
	private JTextField textMail = new JTextField();
	private JPasswordField textPassword = new JPasswordField();
	private JPanel panelName = new JPanel();
	private JPanel panelMail = new JPanel();
	private JPanel panelPassword = new JPanel();
	private JPanel panelButton = new JPanel();
	private PlayerController controller = new PlayerController();
	private JPanel panelCheck = new JPanel();
	private JCheckBox checkAdmin = new JCheckBox("Admin");
	private JCheckBox checkPlayer = new JCheckBox("Joueur");
	
	public Profil(Player p) {
		super();
	//Affectation de Player
		this.player = p;
	//Panel Name
		panelName.add(new JLabel("Login : "));
		textName.setText(p.getName());
		panelName.add(textName);
		textName.setPreferredSize(new Dimension(200,30));
	//Panel Mail
		panelMail.add(new JLabel("Email : "));
		textMail.setText(p.getMail());
		panelMail.add(textMail);
		textMail.setPreferredSize(new Dimension(200,30));
	//Panel Password
		panelPassword.add(new JLabel("Mot de passe : "));
		textPassword.setText(p.getPassword());
		panelPassword.add(textPassword);
		textPassword.setPreferredSize(new Dimension(200,30));
	//Panel Check
		panelCheck.add(checkAdmin);
		panelCheck.add(checkPlayer);
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
		this.add(panelCheck);
		this.add(panelButton);
	}
	
	public Player showJDialog(){
		setVisible(true);
		return player;
	}
	
	class ActionValide implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int admin = 0;
			if(checkAdmin.getText() == "true")
			{
				admin = 1;
			}
			player = new Player(player.getCode(), textName.getText(), textPassword.getText(), textMail.getText(), admin);
			if(controller.security(player) == true)
			{
				try {
					controller.editPlayer(player);
				} catch (DatabaseConnexionException e1) {
					JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donnée");
				} catch (UpdatePlayerException e2) {
					JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la base de données (nombre incohérent)");
					e2.printStackTrace();
				}
				setVisible(false);
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

package fr.quizz.view.player;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.quizz.core.Player;

public class Index extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Player> listPlayer = new ArrayList<>();
	private JButton btnNouveau = new JButton("Nouveau");
	private String title[] = {"Pseudo","Options1","Options2"};
	private Object[][] data = {};
	private JTable tableau = new JTable(data, title);
	private JTextField textName = new JTextField();
	private JTextField textMail = new JTextField();
	private JPasswordField textPassword = new JPasswordField();
	private JPanel panelName = new JPanel();
	private JPanel panelMail = new JPanel();
	private JPanel panelPassword = new JPanel();
	private JPanel panelButton = new JPanel();
	
	public Index(ArrayList<Player> listPlayer) {
		super();
		this.listPlayer = listPlayer;
		
		//Tableau
			this.add(tableau.getTableHeader(), BorderLayout.NORTH);
			this.add(tableau, BorderLayout.CENTER);
	}
	
}

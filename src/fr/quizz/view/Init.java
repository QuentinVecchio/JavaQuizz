package fr.quizz.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Init extends JDialog {
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
			
		}
	}
}

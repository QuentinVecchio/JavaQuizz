package fr.quizz.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import fr.quizz.core.Question;

public class Index extends JPanel {

	private static final long serialVersionUID = 1L;
	//Panel nord
		private JLabel labelBonneRep = new JLabel();
		private JLabel labelMauvaiseRep = new JLabel();
		private JPanel panelHaut = new JPanel();
		private JLabel imageBon = new JLabel(new ImageIcon("bon.png"));
		private JLabel imageMauvais = new JLabel(new ImageIcon("mauvais.png"));
    //Panel central
		private JLabel labelQuestion = new JLabel();
		private JTextField textAnswer = new JTextField();
		private JPanel panelCentral = new JPanel();
		private JPanel panelAnswer =  new JPanel();
		private JPanel panelQuestion = new JPanel();
		private BorderFactory bordure;
    //Panel sud
		private JButton btnValide = new JButton("Valider");
		private JButton btnContinuer = new JButton("Continuer");
		private JButton btnReset = new JButton("RAZ");
		private JPanel panelButton = new JPanel();
	//Variables
		private int nbQuestion = 0;
		private int nbBonneRep = 0;
		private int nbMauvaiseRep = 0;
		private ArrayList<Question> questions = new ArrayList<Question>();
		
	public Index(ArrayList<Question> questions)
	{
		super();
		this.questions = questions;
		this.setLayout(new BorderLayout());
		//PanelNorth
		panelHaut.add(imageBon);
		panelHaut.add(labelBonneRep);
		panelHaut.add(imageMauvais);
		panelHaut.add(labelMauvaiseRep);
		add(panelHaut, BorderLayout.NORTH);
		//PanleCenter
		panelCentral.setLayout(new GridLayout(2,1));
		panelQuestion.setBorder(bordure.createTitledBorder("Question n°1"));
		panelQuestion.add(labelQuestion);
		panelAnswer.add(textAnswer);
		panelCentral.setLayout(new GridLayout(3,1));
		panelCentral.add(panelQuestion);
		panelCentral.add(panelAnswer);
		add(panelCentral, BorderLayout.CENTER);
		//PanelSouth
		panelButton.add(btnReset);
		panelButton.add(btnValide);
		panelButton.add(btnContinuer);
		add(panelButton, BorderLayout.SOUTH);
		
	}
}

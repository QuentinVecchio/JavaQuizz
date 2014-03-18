package fr.quizz.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.quizz.core.Question;
import fr.quizz.core.Sound;

public class Index extends JPanel {

	private static final long serialVersionUID = 1L;
	//Panel nord
		private JLabel labelBonneRep = new JLabel(": 0");
		private JLabel labelMauvaiseRep = new JLabel(": 0");
		private JPanel panelHaut = new JPanel();
		private JLabel imageBon = new JLabel(new ImageIcon("bon.png"));
		private JLabel imageMauvais = new JLabel(new ImageIcon("mauvais.png"));
    //Panel central
		private JLabel labelQuestion = new JLabel();
		private JTextField textAnswer = new JTextField();
		private JPanel panelCentral = new JPanel();
		private JPanel panelAnswer =  new JPanel();
		private JPanel panelQuestion = new JPanel();
    //Panel sud
		private JButton btnValide = new JButton("Valider");
		private JButton btnContinuer = new JButton("Continuer");
		private JButton btnReset = new JButton("RAZ");
		private JPanel panelButton = new JPanel();
	//Variables
		private int indexQuestion = 0;
		private int nbQuestion = 0;
		private int nbBonneRep = 0;
		private int nbMauvaiseRep = 0;
		private ArrayList<Question> questions = new ArrayList<Question>();
		
	public Index()
	{
		super();
		//this.questions = questions;
		nbQuestion = questions.size();
		this.setLayout(new BorderLayout());
		//PanelNorth
		panelHaut.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelHaut.add(imageBon);
		panelHaut.add(labelBonneRep);
		panelHaut.add(imageMauvais);
		panelHaut.add(labelMauvaiseRep);
		add(panelHaut, BorderLayout.NORTH);
		//PanleCenter
		panelCentral.setLayout(new GridLayout(2,1));
		panelQuestion.setBorder(BorderFactory.createTitledBorder("Question n°1"));
		panelQuestion.add(labelQuestion);
		panelAnswer.add(new JLabel("Votre réponse :"));
		panelAnswer.add(textAnswer);
		textAnswer.setPreferredSize(new Dimension(300,30));
		panelCentral.setLayout(new GridLayout(3,1));
		panelCentral.add(panelQuestion);
		panelCentral.add(panelAnswer);
		add(panelCentral, BorderLayout.CENTER);
		//PanelSouth
		panelButton.add(btnReset);
		panelButton.add(btnValide);
		panelButton.add(btnContinuer);
		btnContinuer.enableInputMethods(false);
		add(panelButton, BorderLayout.SOUTH);
	}
	
	public void verif(String GamerAnswer,int index)
	{
		Sound son = new Sound();
		if(questions.get(index).getAnswer() == GamerAnswer)
		{
			//Mise à jour Bonne réponse
			nbBonneRep++;
			//Joue son
			son.run("bonneReponse.mp3");
		}
		else
		{
			//Mise à jour Mauvais réponse
			nbMauvaiseRep++;
			//Joue son
			son.run("mauvaiseReponse.mp3");
		}
		if(indexQuestion != nbQuestion-1)
		{
			indexQuestion++;
			build();
		}
		else
		{
			finish();
		}
	}

	private void build()
	{
		//maj bonne & mauvaise rep
		labelBonneRep.setText(": " + String.valueOf(nbBonneRep));
		labelMauvaiseRep.setText(": "+ String.valueOf(nbMauvaiseRep));
		//maj border
		panelQuestion.setBorder(BorderFactory.createTitledBorder("Question n°"+String.valueOf(indexQuestion + 1)));
		//maj question
		labelQuestion.setText(questions.get(indexQuestion).getText());
		//reset Answer
		textAnswer.setText("");
	}
	
	private void finish()
	{
		
	}
	
	class ActionResetAnswer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			textAnswer.setText("");
		}
	}
	
	
	class ActionContinue implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionSoumettre implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
}


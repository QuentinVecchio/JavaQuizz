package fr.quizz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.quizz.core.Item;
import fr.quizz.core.Player;
import fr.quizz.core.Question;
import fr.quizz.core.Quizz;
import fr.quizz.core.Sound;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.ItemNotSaveException;
import fr.quizz.exception.QuizzNotSaveException;
import fr.quizz.model.ItemModel;
import fr.quizz.model.QuizzModel;

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
		private  JLabel labelAnswer = new JLabel();
    //Panel sud
		private JButton btnValide = new JButton("Valider");
		private JButton btnContinuer = new JButton("Continuer");
		private JButton btnReset = new JButton("RAZ");
		private JPanel panelButton = new JPanel();
	//Variables
		private Player p;
		private QuizzModel modelQuizz = new QuizzModel();
		private ItemModel modelItem = new ItemModel();
		private ArrayList<Item> ListItem = new ArrayList<>();
		private Quizz quizz;
		private int indexQuestion = 0;
		private int nbQuestion = 0;
		private int nbBonneRep = 0;
		private int nbMauvaiseRep = 0;
		private ArrayList<Question> questions = new ArrayList<Question>();
		
	public Index(ArrayList<Question> questions,Player p)
	{
		super();
		this.p = p;
		this.questions = questions;
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
		panelCentral.setLayout(new GridLayout(3,1));
		panelQuestion.setBorder(BorderFactory.createTitledBorder("Question n°1"));
		labelQuestion.setText(this.questions.get(0).getText());
		panelQuestion.add(labelQuestion);
		JPanel panel = new JPanel(new GridLayout(2,1));
		panelAnswer.add(new JLabel("Votre réponse :"));
		panelAnswer.add(textAnswer);
		textAnswer.setPreferredSize(new Dimension(300,30));
		panelCentral.setLayout(new GridLayout(3,1));
		panelCentral.add(panelQuestion);
		panel.add(panelAnswer);
		panel.add(labelAnswer);
		labelAnswer.setHorizontalAlignment(JLabel.CENTER);
		labelAnswer.setVerticalAlignment(JLabel.CENTER);
		panelCentral.add(panel);
		add(panelCentral, BorderLayout.CENTER);
		//PanelSouth
		panelButton.add(btnReset);
		panelButton.add(btnValide);
		panelButton.add(btnContinuer);
		btnValide.addActionListener(new ActionSoumettre());
		btnContinuer.addActionListener(new ActionContinue());
		btnReset.addActionListener(new ActionResetAnswer());
		btnContinuer.setVisible(false);
		add(panelButton, BorderLayout.SOUTH);
	}
	
	public void verif()
	{
		//Enregistrement de la réponse
		ListItem.add(new Item(questions.get(indexQuestion).getCode(), -1, textAnswer.getText()));
		Sound son = new Sound();
		if(questions.get(indexQuestion).getAnswer().equals(textAnswer.getText()))
		{
			labelAnswer.setForeground(new Color(51,204,0));
			//Mise à jour Bonne réponse
			nbBonneRep++;
			//Joue son
			son.run("gagne.wav");
		}
		else
		{
			labelAnswer.setForeground(new Color(204,0,0));
			//Mise à jour Mauvais réponse
			nbMauvaiseRep++;
			//Joue son
			son.run("perd.wav");
		}
		labelBonneRep.setText(": " + String.valueOf(nbBonneRep));
		labelMauvaiseRep.setText(": "+ String.valueOf(nbMauvaiseRep));
		labelAnswer.setText("La réponse était " + questions.get(indexQuestion).getAnswer());
	}

	private void build()
	{
		//maj border
		panelQuestion.setBorder(BorderFactory.createTitledBorder("Question n°"+String.valueOf(indexQuestion + 1)));
		//maj question
		labelQuestion.setText(questions.get(indexQuestion).getText());
		//reset Answer
		textAnswer.setText("");
		//reset LabelAnswer
		labelAnswer.setText("");
	}
	
	private void finish() throws DatabaseConnexionException, QuizzNotSaveException, ItemNotSaveException
	{
		Calendar c = Calendar.getInstance();
		this.quizz = new Quizz(-1,nbQuestion,c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH),nbBonneRep,p.getCode());
		//Remplissage des code_quizz
		int indexQuizz = modelQuizz.saveQuizz(quizz);
		for(int i=0;i<= indexQuestion;i++)
		{
			ListItem.get(i).setCode_quizz(indexQuizz);
			modelItem.saveItem(ListItem.get(i));
		}
		String texte = "Fin du Quizz.\nRésultat : \n";
		texte+= "Bonne réponse : " + String.valueOf(nbBonneRep) + "/" + String.valueOf(nbQuestion) + "\n";
		texte+= "Mauvaise réponse : " + String.valueOf(nbMauvaiseRep) + "/" + String.valueOf(nbQuestion) + "\n";
		JOptionPane.showMessageDialog(null, texte, "Fin du Quizz", JOptionPane.DEFAULT_OPTION);
		this.panelCentral.removeAll();
		this.panelButton.removeAll();
		this.panelQuestion.removeAll();
		this.panelAnswer.removeAll();
		this.panelHaut.removeAll();
		this.validate();
	}
	
	class ActionResetAnswer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			textAnswer.setText("");
		}
	}
	
	
	class ActionContinue implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(indexQuestion != nbQuestion-1)
			{
				indexQuestion++;
				build();
				btnValide.setVisible(true);
			}
			else
			{
				try {
					finish();
				} catch (DatabaseConnexionException | QuizzNotSaveException
						| ItemNotSaveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			btnContinuer.setVisible(false);
		}
	}
	
	class ActionSoumettre implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			verif();
			btnContinuer.setVisible(true);
			btnValide.setVisible(false);
		}
	}
	
}


package fr.quizz.view.question;

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

import fr.quizz.controller.QuestionController;
import fr.quizz.core.Question;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.QuestionNotSaveException;

public class Add extends JDialog {

	private static final long serialVersionUID = 1L;
	private Question question;
	private JButton btnValide = new JButton("Valider");
	private JButton btnReset = new JButton("RAZ");
	private JTextField textQuestion = new JTextField();
	private JTextField textAnswer = new JTextField();
	private JPanel panelQuestion = new JPanel();
	private JPanel panelAnswer = new JPanel();
	private JPanel panelButton = new JPanel();
	private QuestionController controller = new QuestionController();
	public Add() {
		super();
		setTitle("Ajout d'une question");
		setSize(new Dimension(250,290));
		setLocationRelativeTo(null);
		setModal(true);
		//Panel Question
			panelQuestion.add(new JLabel("Question : "));
			panelQuestion.add(textQuestion);
			textQuestion.setPreferredSize(new Dimension(200,30));
		//Panel Answer
			panelAnswer.add(new JLabel("Réponse : "));
			panelAnswer.add(textAnswer);
			textAnswer.setPreferredSize(new Dimension(200,30));
		//Panel Button
			panelButton.add(btnReset);
			panelButton.add(btnValide);
			btnReset.addActionListener(new ActionReset());
			btnValide.addActionListener(new ActionValide());
		//Panel Main
			this.setLayout(new GridLayout(3,1));
			this.add(panelQuestion);
			this.add(panelAnswer);
			this.add(panelButton);
	}
	
	public Question showJDialog(){
		setVisible(true);
		return question;
	}
	
	class ActionValide implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			question = new Question(-1, textQuestion.getText(), textAnswer.getText());
			
			try {
				question.setCode(controller.addQuestion(question));
			} catch (DatabaseConnexionException e1) {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (QuestionNotSaveException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur en base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
			if(question.getCode() > -1){
				JOptionPane.showMessageDialog(null, "La question a été créé", "Information", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				
			}else{
				JOptionPane.showMessageDialog(null, "La question n'a pas été créé", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ActionReset implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			textQuestion.setText("");
			textAnswer.setText("");
		}
	}
}

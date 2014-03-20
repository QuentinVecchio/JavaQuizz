package fr.quizz.view.question;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.quizz.controller.QuestionController;
import fr.quizz.core.Question;

public class Edit extends JDialog {

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
	
	public Edit(Question q) {
		super();
		setTitle("Edition d'une question");
		setSize(new Dimension(250,290));
		setLocationRelativeTo(null);
		setModal(true);
		
	//Affectation de Question
		this.question = q;
	//Panel Question
		panelQuestion.add(new JLabel("Question : "));
		textQuestion.setText(q.getText());
		panelQuestion.add(textQuestion);
		textQuestion.setPreferredSize(new Dimension(200,30));
	//Panel Answer
		panelAnswer.add(new JLabel("Réponse : "));
		textAnswer.setText(q.getAnswer());
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
			question = new Question(question.getCode(), textQuestion.getText(), textAnswer.getText());
			if(controller.security(question)){
				setVisible(false);					
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

package fr.quizz.view.question;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.quizz.core.Question;

public class ManageQuestion extends JPanel{

	private static final long serialVersionUID = 5571567974713331949L;

	private ArrayList<Question> questionList = new ArrayList<Question>();
	
	private JButton btnAddQuestion = new JButton("Ajouter une question");
	
	private JTable playerTable;
	
	private ManageTableQuestion manageTable;
	
	public ManageQuestion(ArrayList<Question> questionList) {
		super();
		this.setQuestionList(questionList);
		manageTable = new ManageTableQuestion(questionList);
		initComponent();

	}
	
	public void initComponent(){
		setLayout(new BorderLayout());
		btnAddQuestion.addActionListener(new ActionAddQuestion());
		add(btnAddQuestion, BorderLayout.SOUTH);
		
		playerTable = new JTable(manageTable);
		add(new JScrollPane(playerTable), BorderLayout.NORTH);
		
		setVisible(true);
	}

	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}

	class ActionAddQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*final Add add = new Add();
			final Player p = add.showJDialog();
			if(p != null){
				manageTable.addQuestion(p);
			}*/
		}
	}
}

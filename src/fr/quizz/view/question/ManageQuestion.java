package fr.quizz.view.question;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.quizz.controller.QuestionController;
import fr.quizz.core.Question;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.UpdateException;
import fr.quizz.view.question.Edit;

public class ManageQuestion extends JPanel{

	private static final long serialVersionUID = 5571567974713331949L;

	private ArrayList<Question> questionList = new ArrayList<Question>();
	
	private JButton btnAddQuestion = new JButton("Ajouter");
	
	private JButton btnEditQuestion = new JButton("Editer");
	
	private JButton btnDeleteQuestion = new JButton("Supprimer");
	
	private JTable questionTable;
	
	private ManageTableQuestion manageTable;
	
	private QuestionController controller = new QuestionController();
	
	public ManageQuestion(ArrayList<Question> questionList) {
		super();
		this.setQuestionList(questionList);
		manageTable = new ManageTableQuestion(questionList);
		initComponent();

	}
	
	public void initComponent(){
		setLayout(new BorderLayout());
		JPanel panOptions = new JPanel(new FlowLayout());
		btnAddQuestion.addActionListener(new ActionAddQuestion());
		panOptions.add(btnAddQuestion);
		
		btnEditQuestion.addActionListener(new ActionEditQuestion());
		panOptions.add(btnEditQuestion);
		
		btnDeleteQuestion.addActionListener(new ActionDeleteQuestion());
		panOptions.add(btnDeleteQuestion);
		
		add(panOptions, BorderLayout.SOUTH);
		
		questionTable = new JTable(manageTable);
		questionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(questionTable), BorderLayout.CENTER);
		
		setVisible(true);
	}

	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}

	/**
	 *	Action to delete a player from the database
	 */
	class ActionDeleteQuestion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			final int idInTable = questionTable.getSelectedRow();
			if(idInTable >= 0 && idInTable < questionList.size()){
				final int answer = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer ?", "Titre", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(answer == 0){
					try {
						controller.deleteQuestion(idInTable);
						manageTable.removeQuestion(idInTable);
					} catch (DatabaseConnexionException e) {
						JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donnée");
					} catch (DeleteMultipleException e) {
						JOptionPane.showMessageDialog(null, "Erreur: suppression de plusieurs éléments !");
					}
				}
			}
		}
	}
	
	class ActionEditQuestion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			final int idInTable = questionTable.getSelectedRow();
			if(idInTable > 0){
				Edit edit = new Edit(questionList.get(idInTable));
				final Question question = edit.showJDialog();
				try {
					controller.editQuestion(question);
					manageTable.updateQuestion(idInTable, question);
				} catch (DatabaseConnexionException e) {
					JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donnée");
				}catch (UpdateException e) {
					JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la base de données (nombre incohérent)");
					e.printStackTrace();
				}
			}
		}
	}
	
	class ActionAddQuestion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			final Add add = new Add();
			final Question q = add.showJDialog();
			if(q != null){
				manageTable.addQuestion(q);
			}
		}
	}
}

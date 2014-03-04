package fr.quizz.view.question;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fr.quizz.core.Question;

public class ManageTableQuestion extends AbstractTableModel {

	private static final long serialVersionUID = -5799896863128212772L;

	private String[] entete = {"Question", "Réponse"};
	
	private ArrayList<Question> questionList = new ArrayList<Question>();
	
	public ManageTableQuestion(ArrayList<Question> list) {
		this.setQuestionList(list);
	}

	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {
		return questionList.size();
	}

	@Override
	public String getColumnName(int column){
		return entete[column];
	}
	
	@Override
	public Object getValueAt(int line, int column) {
		switch (column) {
		case 0:
			return questionList.get(line).getText();
		case 1:
			return questionList.get(line).getAnswer();
		default:
			break;
		}
		return null;
	}

	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<Question> playerList) {
		this.questionList = playerList;
		fireTableDataChanged();
	}
	
	public void addQuestion(Question q){
		this.questionList.add(q);
		fireTableDataChanged();
	}

}

package fr.quizz.core;

import java.sql.Date;
import java.util.ArrayList;

public class Quizz {
	private int code = -1;
	private int numberQuestions;
	private ArrayList<String> answers;
	private Date date;
	private int score = 0;
	
	/**
	 * @param code
	 * @param numberQuestions
	 * @param answers
	 * @param date
	 * @param score
	 */
	public Quizz(int code, int numberQuestions, ArrayList<String> answers,
			Date date, int score) {
		super();
		this.code = code;
		this.numberQuestions = numberQuestions;
		this.answers = answers;
		this.date = date;
		this.score = score;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getNumberQuestions() {
		return numberQuestions;
	}

	public void setNumberQuestions(int numberQuestions) {
		this.numberQuestions = numberQuestions;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Quizz [code=" + code + ", numberQuestions=" + numberQuestions
				+ ", answers=" + answers + ", date=" + date + ", score="
				+ score + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quizz other = (Quizz) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (code != other.code)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (numberQuestions != other.numberQuestions)
			return false;
		if (score != other.score)
			return false;
		return true;
	}	
}

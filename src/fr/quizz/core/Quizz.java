package fr.quizz.core;

import java.sql.Date;

public class Quizz {
	private int code = -1;
	private int numberQuestions;
	private Date date;
	private int score = 0;
	private int code_joueur;
	
	/**
	 * @param code
	 * @param numberQuestions
	 * @param answers
	 * @param date
	 * @param score
	 */
	public Quizz(int code, int numberQuestions,	Date date, int score, int code_joueur) {
		super();
		this.code = code;
		this.numberQuestions = numberQuestions;
		this.date = date;
		this.score = score;
		this.code_joueur = code_joueur;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCode_joueur() {
		return code_joueur;
	}

	public void setCode_joueur(int code_joueur) {
		this.code_joueur = code_joueur;
	}

	@Override
	public String toString() {
		return "Quizz [code=" + code + ", numberQuestions=" + numberQuestions
				+ ", date=" + date + ", score=" + score + ", code_joueur="
				+ code_joueur + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + code_joueur;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + numberQuestions;
		result = prime * result + score;
		return result;
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
		if (code != other.code)
			return false;
		if (code_joueur != other.code_joueur)
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

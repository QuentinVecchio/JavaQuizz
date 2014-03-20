package fr.quizz.core;

public class Item {

	private int code_question;
	
	private int code_quizz;
	
	private String reponse_joueur;

	public Item(int code_question, int code_quizz, String reponse_joueur) {
		super();
		this.code_question = code_question;
		this.code_quizz = code_quizz;
		this.reponse_joueur = reponse_joueur;
	}

	public int getCode_question() {
		return code_question;
	}

	public void setCode_question(int code_question) {
		this.code_question = code_question;
	}

	public int getCode_quizz() {
		return code_quizz;
	}

	public void setCode_quizz(int code_quizz) {
		this.code_quizz = code_quizz;
	}

	public String getReponse_joueur() {
		return reponse_joueur;
	}

	public void setReponse_joueur(String reponse_joueur) {
		this.reponse_joueur = reponse_joueur;
	}

	@Override
	public String toString() {
		return "Item [code_question=" + code_question + ", code_quizz="
				+ code_quizz + ", reponse_joueur=" + reponse_joueur + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code_question;
		result = prime * result + code_quizz;
		result = prime * result
				+ ((reponse_joueur == null) ? 0 : reponse_joueur.hashCode());
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
		Item other = (Item) obj;
		if (code_question != other.code_question)
			return false;
		if (code_quizz != other.code_quizz)
			return false;
		if (reponse_joueur == null) {
			if (other.reponse_joueur != null)
				return false;
		} else if (!reponse_joueur.equals(other.reponse_joueur))
			return false;
		return true;
	}
	
	
}

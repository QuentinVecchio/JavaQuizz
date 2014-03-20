package fr.quizz.core;

public class Player {
	private int code = -1;
	private String name;
	private String password;
	private String mail;
	private int admin = 0;
	/**
	 *  
	 * @param code
	 * @param name
	 * @param password
	 * @param mail
	 */
	public Player(int code, String name, String password, String mail, int admin) {
		super();
		this.code = code;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.admin = admin;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Player [code=" + code + ", name=" + name + ", password="
				+ password + ", mail=" + mail + ", admin=" + admin + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (admin != other.admin)
			return false;
		if (code != other.code)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}	
}

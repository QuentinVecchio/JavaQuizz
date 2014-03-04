package fr.quizz.view.player;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fr.quizz.core.Player;

public class ManageTable extends AbstractTableModel {

	private static final long serialVersionUID = 880100311315812730L;

	private String[] entete = {"Nom", "Adresse mail"};
	
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public ManageTable(ArrayList<Player> list) {
		this.setPlayerList(list);
	}

	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {
		return playerList.size();
	}

	@Override
	public String getColumnName(int column){
		return entete[column];
	}
	
	@Override
	public Object getValueAt(int line, int column) {
		switch (column) {
		case 0:
			return playerList.get(line).getName();
		case 1:
			return playerList.get(line).getMail();
		default:
			break;
		}
		return null;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
		fireTableDataChanged();
	}
	
	public void addPlayer(Player p){
		this.playerList.add(p);
		fireTableDataChanged();
	}
	
	public void updatePlayer(int id, Player p){
		this.playerList.set(id, p);
		fireTableDataChanged();
	}

	public void removePlayer(int idInTable) {
		this.playerList.remove(idInTable);
		fireTableDataChanged();
	}

}

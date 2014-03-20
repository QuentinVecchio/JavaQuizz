package fr.quizz.view.item;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fr.quizz.core.Item;

public class ManageTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] entete = {"Question", "Quizz","Réponse"};
	private ArrayList<Item> itemList = new ArrayList<Item>();
	
	public ManageTable(ArrayList<Item> list) {
		this.setItemList(list);
	}
	
	public ArrayList<Item> getPlayerList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {
		return itemList.size();
	}

	@Override
	public String getColumnName(int column){
		return entete[column];
	}

	@Override
	public Object getValueAt(int line, int column) {
		switch (column) {
		case 0:
			return itemList.get(line).getCode_question();
		case 1:
			return itemList.get(line).getCode_quizz();
		case 2:
			return itemList.get(line).getReponse_joueur();
		default:
			break;
		}
		return null;
	}
	
	public void addPlayer(Item it){
		this.itemList.add(it);
		fireTableDataChanged();
	}
	
	public void updatePlayer(int id, Item it){
		this.itemList.set(id, it);
		fireTableDataChanged();
	}

	public void removePlayer(int idInTable) {
		this.itemList.remove(idInTable);
		fireTableDataChanged();
	}
}

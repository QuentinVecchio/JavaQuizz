package fr.quizz.view.item;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import fr.quizz.core.Item;
import fr.quizz.view.item.ManageTable;

public class ManageItem extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	
	private JTable itemTable;
	
	private ManageTable manageTable;
	
	public ManageItem(ArrayList<Item> itemList) {
		super();
		this.setItemList(itemList);
		manageTable = new ManageTable(itemList);
		initComponent();

	}
	
	public void initComponent(){
		setLayout(new BorderLayout());
		itemTable = new JTable(manageTable);
		itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(itemTable), BorderLayout.CENTER);
		setVisible(true);
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
}

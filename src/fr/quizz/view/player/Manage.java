package fr.quizz.view.player;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.quizz.core.Player;

public class Manage extends JPanel{

	private static final long serialVersionUID = -6880840401682350191L;

	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	private JButton btnAdd = new JButton("Ajouter");
	
	private JTable playerTable;
	
	private ManageTable manageTable;
	
	public Manage(ArrayList<Player> playerList) {
		super();
		this.setPlayerList(playerList);
		manageTable = new ManageTable(playerList);
		initComponent();

	}
	
	public void initComponent(){
		setLayout(new BorderLayout());
		btnAdd.addActionListener(new ActionAdd());
		add(btnAdd, BorderLayout.SOUTH);
		
		playerTable = new JTable(manageTable);
		add(new JScrollPane(playerTable), BorderLayout.NORTH);
		
		setVisible(true);
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	class ActionAdd implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			final Add add = new Add();
			final Player p = add.showJDialog();
			if(p != null){
				manageTable.addPlayer(p);
			}
		}
	}
	
}

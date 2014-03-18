package fr.quizz.view.player;

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

import fr.quizz.controller.PlayerController;
import fr.quizz.core.Player;
import fr.quizz.exception.DatabaseConnexionException;
import fr.quizz.exception.DeleteMultipleException;
import fr.quizz.exception.UpdatePlayerException;

public class ManagePlayer extends JPanel{

	private static final long serialVersionUID = -6880840401682350191L;

	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	private JButton btnAdd = new JButton("Ajouter");
	
	private JButton btnEdit = new JButton("Editer");
	
	private JButton btnDelete = new JButton("Supprimer");
	
	private JTable playerTable;
	
	private ManageTable manageTable;
	
	private PlayerController controller = new PlayerController();
	
	public ManagePlayer(ArrayList<Player> playerList) {
		super();
		this.setPlayerList(playerList);
		manageTable = new ManageTable(playerList);
		initComponent();

	}
	
	/**
	 * Init the GUI component
	 */
	public void initComponent(){
		setLayout(new BorderLayout());

		JPanel panOptions = new JPanel(new FlowLayout());
		
		btnAdd.addActionListener(new ActionAddPlayer());
		panOptions.add(btnAdd);
		btnEdit.addActionListener(new ActionEditPlayer());
		panOptions.add(btnEdit);
		btnDelete.addActionListener(new ActionDeletePlayer());
		panOptions.add(btnDelete);
		
		add(panOptions, BorderLayout.SOUTH);
		
		playerTable = new JTable(manageTable);
		playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(playerTable), BorderLayout.CENTER);
		
		setVisible(true);
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}


	/**
	 *	Action to delete a player from the database
	 */
	class ActionDeletePlayer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			final int idInTable = playerTable.getSelectedRow();
			if(idInTable >= 0 && idInTable < playerList.size()){
				final int answer = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer ?", "Titre", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(answer == 0){
					try {
						controller.deletePlayer(manageTable.getPlayerList().get(idInTable).getCode());
						manageTable.removePlayer(idInTable);
					} catch (DatabaseConnexionException e) {
						JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donnée");
					} catch (DeleteMultipleException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erreur: suppression de plusieurs éléments !");
					}
				}
			}
		}
	}
	
	class ActionEditPlayer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			final int idInTable = playerTable.getSelectedRow();
			if(idInTable > 0){
				Edit edit = new Edit(playerList.get(idInTable));
				final Player player = edit.showJDialog();
				try {
					controller.editPlayer(player);
					manageTable.updatePlayer(idInTable, player);
				} catch (DatabaseConnexionException e) {
					JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donnée");
				} catch (UpdatePlayerException e) {
					JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la base de données (nombre incohérent)");
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Launch a JDialog to get the informations about the new player and if it is successfull, add to the JTable
	 */
	class ActionAddPlayer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			final Add add = new Add();
			final Player p = add.showJDialog();
			if(p != null){
				manageTable.addPlayer(p);
			}
		}
	}
	
}

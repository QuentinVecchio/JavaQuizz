package fr.quizz.controller;

import javax.swing.JPanel;

import fr.quizz.core.Player;
import fr.quizz.view.dashboard.Dashboard;
import fr.quizz.view.player.Add;


public class DashboardController extends Controller {
	public void dashboard(Player p){
		Dashboard d = new Dashboard(this, p);
		d.showFrame();
	}
	
	public JPanel showAddPlayerPanel()
	{
		Add panelAddPlayer = new Add();
		return panelAddPlayer;
	}
}

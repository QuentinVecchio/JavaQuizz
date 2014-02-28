package fr.quizz.controller;

import fr.quizz.core.Player;
import fr.quizz.view.dashboard.Dashboard;


public class DashboardController extends Controller {
	public void dashboard(Player p){
		Dashboard d = new Dashboard(this, p);
		d.showFrame();
	}
}

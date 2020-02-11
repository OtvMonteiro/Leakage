package io.github.otvmonteiro.leak;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class randomLeak {
	private static int probability = 10; //starting at 1 in 10 chance of leak, every scheduler run
	public int getProbability() {return probability;}
	public static CommandExecutor setProbability(int newp) {
		probability=newp;
		return null;
	}
	
	public static Player getRandomPlayer() {
		Bukkit.broadcastMessage("Assigning random player");
		Random r = new Random();
		int n = r.nextInt(Bukkit.getOnlinePlayers().size());
		Player player =  (Player) ((Bukkit.getOnlinePlayers().toArray())[n]);
		return player;
	}
	
	
	
	
	

}

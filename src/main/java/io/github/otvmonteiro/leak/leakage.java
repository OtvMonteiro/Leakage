package io.github.otvmonteiro.leak;

import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class leakage extends JavaPlugin{
	@Override
	public void onEnable(){
		getCommand("leak").setExecutor(new leakCommand());
		getCommand("upperbound").setExecutor(new upperboundCommand()); 	
		getCommand("leakProbability").setExecutor(new probabilityCommand());

		
		
		// Scheduler to activate leakage at random times (probability set by user)
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				//Randomize 
				Random r = new Random();
				int prob = probabilityCommand.getProbability();
				int n = r.nextInt(prob);
				//Create leakage randomly
				if(n==prob) {
					new createLeak(randomLeak.getRandomPlayer());
				}
			}
		}, 0L, 1 * 20L);

		
	}
	
}


package io.github.otvmonteiro.leak;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class leakage extends JavaPlugin{
	@Override
	public void onEnable(){
		getCommand("leak").setExecutor(new leakCommand());
		getCommand("upperbound").setExecutor(new upperboundCommand()); 	
		getCommand("leakProbability").setExecutor(new probabilityCommand());
		getCommand("schedulerTime").setExecutor(new schedulerTimeCommand());
		
		
		// Scheduler to activate leakage at random times (probability set by user)
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				//Debug time scheduler
				//Bukkit.broadcastMessage("Scheduler is running now.");
				//Randomize 
				Random r = new Random();
				int prob = probabilityCommand.getProbability();
				int n = r.nextInt(prob+1);
				//Create leakage randomly
				if(n==prob && Bukkit.getOnlinePlayers().size()>0) {
					Player p = randomLeak.getRandomPlayer();
					new createLeak(p);
				}
			}
		}, 0L, schedulerTimeCommand.getSchedulerTime() * 20L);

		
	}
	
}


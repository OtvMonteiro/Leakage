package io.github.otvmonteiro.leak;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class leakage extends JavaPlugin{
	private static long sTime;
	@Override
	public void onEnable(){
		//Listens to an issued command
		getCommand("leak").setExecutor(new leakCommand());
		getCommand("upperbound").setExecutor(new upperboundCommand()); 	
		getCommand("leakProbability").setExecutor(new probabilityCommand());
		getCommand("schedulerTime").setExecutor(new schedulerTimeCommand());
		
		//Gets scheduler time
		sTime=(long) (schedulerTimeCommand.getSchedulerTime())*20; //SchedulerTime times 20 ticks
		
		//Script use to activate a random leak, used in sequence 
		Runnable script = new Runnable() {
	        @Override
	        public void run()
	        {
	        	
				//Randomize 
				Random r = new Random();
				int prob = probabilityCommand.getProbability();
				int n = r.nextInt(prob+1);
				
				//Create leakage randomly
				if(n==prob && Bukkit.getOnlinePlayers().size()>0) {
					Player p = randomLeak.getRandomPlayer();
					new createLeak(p);
				}
				//Tests if scheduled time changed
				if (sTime!=((long) (schedulerTimeCommand.getSchedulerTime())*20)) {
					sTime=(long) (schedulerTimeCommand.getSchedulerTime())*20; //SchedulerTime times 20 ticks
					restartScheduler();
				}
			}
	      };
					
		//FIRST SCHEDULER, that might get reset after
		// Scheduler to activate leakage at random times (probability set by user)
		Bukkit.getServer().getScheduler().runTaskTimer(this,script, 20,sTime); // cycle: value of SchedulerTime in seconds
	}
	
	
	/// Restarts the task when there is a new value of SchedulerTime >> cancels the task, get the new value and create new task with that value
	private void restartScheduler() {
		//
		Bukkit.getServer().getScheduler().cancelTasks(this);
		sTime=(long) (schedulerTimeCommand.getSchedulerTime())*20;
		//Creates the new task
		Bukkit.getServer().getScheduler().runTaskTimer(this,new Runnable() {
	        @Override
	        public void run()
	        {
				//Randomize 
				Random r = new Random();
				int prob = probabilityCommand.getProbability();
				int n = r.nextInt(prob+1);
			
				//Create leakage randomly
				if(n==prob && Bukkit.getOnlinePlayers().size()>0) {
					Player p = randomLeak.getRandomPlayer();
					new createLeak(p);
				}
				//Tests if scheduled time changed
				if (sTime!=((long) (schedulerTimeCommand.getSchedulerTime())*20)) {
					sTime=(long) (schedulerTimeCommand.getSchedulerTime())*20; //SchedulerTime times 20 ticks
					restartScheduler();
				}
				//ends run
			}
	      }, sTime,sTime);//Since it just ran the scheduler, waits for the new time and then cycles with it
		return;
	}
	
	
}

	

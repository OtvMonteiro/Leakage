package io.github.otvmonteiro.leak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class schedulerTimeCommand implements CommandExecutor{

	private static int time = 300; //Time to new schedule run is 300 seconds
	public static int getSchedulerTime() {return time;}
	private int argument;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// Resolving argument
		if(!args[0].isEmpty()) {//In case something is written it sets the new value
			argument=Integer.parseInt(args[0]);
		}
		else {                  //Otherwise the original is reset (to notify sender)
			argument = getSchedulerTime();
		}


		schedulerTimeCommand.setschedulerTime(argument);
		
		if (!(sender instanceof Player)) {Bukkit.getConsoleSender().sendMessage("Time between random leak check is set to "+args[0]);	}
		else {Player player = (Player) sender;		player.chat("Time between random leak check is set to "+args[0]);}
		return true;
	}
	public static CommandExecutor setschedulerTime(int newp) {
		if(newp<1){newp=1;}//Clearing null case
		time=newp;
		return null;
	}
 
}

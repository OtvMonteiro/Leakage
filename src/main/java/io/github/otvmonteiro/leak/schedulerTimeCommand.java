package io.github.otvmonteiro.leak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class schedulerTimeCommand implements CommandExecutor{

	private static int time = 300; //Time to new schedule run is 300 seconds
	public static int getSchedulerTime() {return time;}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		schedulerTimeCommand.setschedulerTime(Integer.parseInt(args[0]));
		
		if (!(sender instanceof Player)) {Bukkit.broadcastMessage("Time between random leak check is set to "+args[0]);	}
		else {Player player = (Player) sender;		player.chat("Time between random leak check is set to "+args[0]);}
		return true;
	}
	public static CommandExecutor setschedulerTime(int newp) {
		if(newp<1){newp=1;}//Clearing null case
		time=newp;
		return null;
	}
 
}

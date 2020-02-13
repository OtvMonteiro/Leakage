package io.github.otvmonteiro.leak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class probabilityCommand implements CommandExecutor{

	private static int probability = 10; //starting at 1 in 10 chance of leak, every scheduler run
	private int argument;
	
	public static int getProbability() {return probability;}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Resolving argument
		if(!args[0].isEmpty()) {//In case something is written it sets the new value
			argument=Integer.parseInt(args[0]);
		}
		else {                  //Otherwise the original is reset (to notify sender)
			argument = getProbability();
		}
		
		probabilityCommand.setProbability(argument);
		
		//Chooses between server and player to notify
		if (!(sender instanceof Player)) {Bukkit.getConsoleSender().sendMessage("Probability set to 1 in "+args[0]);}
		else {Player player = (Player) sender;		player.chat("Probability set to 1 in "+args[0]);}
		return true;
	}
	public static CommandExecutor setProbability(int newp) {
		if(newp<1){newp=1;}//Clearing null case
		probability=newp;
		return null;
	}
 
}

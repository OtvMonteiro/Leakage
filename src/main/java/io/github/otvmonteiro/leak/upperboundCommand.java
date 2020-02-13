package io.github.otvmonteiro.leak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class upperboundCommand implements CommandExecutor{

	private int argument;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// Resolving argument
		if(!args[0].isEmpty()) {//In case something is written it sets the new value
			argument=Integer.parseInt(args[0]);
		}
		else {                  //Otherwise the original is reset (to notify sender)
			argument = leakCommand.getUpperbound();
		}
		
		leakCommand.setUpperbound(argument);
		
		
		if (!(sender instanceof Player)) {Bukkit.getConsoleSender().sendMessage("Upperbound set to : "+args[0]);	}
		else {Player player = (Player) sender;		player.chat("Upperbound set to : "+args[0]);   }
		return true;
	}
 
}

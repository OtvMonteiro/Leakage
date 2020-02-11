package io.github.otvmonteiro.leak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class upperboundCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		
		leakCommand.setUpperbound(Integer.parseInt(args[0]));
		
		
		if (!(sender instanceof Player)) {Bukkit.broadcastMessage("Upperbound set to : "+args[0]);	}
		else {Player player = (Player) sender;		player.chat("Upperbound set to : "+args[0]);   }
		return true;
	}
 
}

package io.github.otvmonteiro.leak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class upperboundCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		leakCommand.setUpperbound(Integer.parseInt(args[0]));
		Bukkit.broadcastMessage("Upperbound set to : "+args[0]);
		
		return true;
	}
 
}

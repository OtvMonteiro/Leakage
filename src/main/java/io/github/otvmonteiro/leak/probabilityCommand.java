package io.github.otvmonteiro.leak;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class probabilityCommand implements CommandExecutor{

	private static int probability = 10; //starting at 1 in 10 chance of leak, every scheduler run
	public static int getProbability() {return probability;}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		probabilityCommand.setProbability(Integer.parseInt(args[0]));
		
		return true;
	}
	public static CommandExecutor setProbability(int newp) {
		if(newp<1){newp=1;}//Clearing null case
		probability=newp;
		return null;
	}
 
}

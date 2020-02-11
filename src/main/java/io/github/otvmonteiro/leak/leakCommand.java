package io.github.otvmonteiro.leak;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class leakCommand implements CommandExecutor {
	
	private static int upperbound=10;
	public int getUpperbound () {return upperbound;}
	public static CommandExecutor setUpperbound (int upperbound) {
		leakCommand.upperbound=upperbound;
		return null;
		}
	
	public boolean onCommand(CommandSender sender, Command cmnd, String alias, String[] args) {
		Player player;
		//This command can only be executed by Players, or called by code
		if (!(sender instanceof Player)) {
			//Get`s a random player as "sender" (that leakage occurs near)
			player = randomLeak.getRandomPlayer();
		}
		else {
			//Cast the command sender to Player
			player = (Player) sender;
		}
		
		//Creates Leakage
		new createLeak(player);
		
		return true;
	}
}
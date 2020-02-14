package io.github.otvmonteiro.leak;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class leakCommand implements CommandExecutor {
	
	private static int upperbound=10;
	public static int getUpperbound () {return upperbound;}
	public static CommandExecutor setUpperbound (int upperbound) {
		leakCommand.upperbound=upperbound;
		return null;
		}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmnd, String alias, String[] args) {
		Player player;
		//This command can only be executed by Players, or called by code
		if (!(sender instanceof Player)) {
			//Get`s a random player as "sender" (that leakage occurs near) or notify there are no players
			if(Bukkit.getOnlinePlayers().size()>0) {
				// If issued by Console on an assigned online player
				if(args.length!=0 && Bukkit.getPlayer(args[0]).isOnline()){
					player = Bukkit.getPlayer(args[0]);
				}
				//Assign a random player
				else{player = randomLeak.getRandomPlayer();}
				
				//Creates Leakage
				new createLeak(player);
				
			}else {Bukkit.getConsoleSender().sendMessage("There are no players available");}	
			
		}
		
		// In case it was issued by a player
		else {
			//Cast the command sender to Player
			player = (Player) sender;
			//Creates Leakage
			new createLeak(player);
		}
		
		return true;
	}
}
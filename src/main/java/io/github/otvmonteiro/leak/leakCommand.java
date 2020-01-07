package io.github.otvmonteiro.leak;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Random;



public class leakCommand implements CommandExecutor {
	
	private static int upperbound=10;
	public int getUpperbound () {return upperbound;}
	public static CommandExecutor setUpperbound (int upperbound) {
		leakCommand.upperbound=upperbound;
		return null;
		}
	
	public boolean onCommand(CommandSender sender, Command cmnd, String alias, String[] args) {
		//This command can only be executed by Players
		if (!(sender instanceof Player)) {
			return false;
		}
		//Cast the command sender to a Player
		Player player = (Player) sender;
		//Get player location
		Location loc = getRandomLocation(player.getLocation());
		//Creates the SpawningBlock in the desired position
	//	CreatureSpawner spawner = (CreatureSpawner) loc.getBlock();//APARENTEMENTE N POSSO FAZER ESSE CAST
	//ERROR>> Caused by: java.lang.ClassCastException: org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock cannot be cast to org.bukkit.block.CreatureSpawner

		//spawner.setCreatureTypeByName("Cow");//
		explodePlace(loc);
		Block block = loc.getBlock();
		block.setTypeId(52);
		//spawner.setTypeId(92);
		
		Bukkit.broadcastMessage(ChatColor.RED+"Leakage appeard!!!");
		
		return true;
	}
	
	//Creates a random location, based on player's location
	private Location getRandomLocation(Location p) {
		//Get random addition (from -upperbound to +upperbound for X and Y and 0 to +upperbound for Z)
		Random rand1 = new Random();int n1 = rand1.nextInt(getUpperbound()*2) - getUpperbound();
		Random rand2 = new Random();int n2 = rand2.nextInt(getUpperbound()*2) - getUpperbound();
		Random rand3 = new Random();int n3 = rand3.nextInt(getUpperbound()/2);//Not too high
	
		Location loc = new Location(p.getWorld(), p.getX()+n1, p.getY()+n2,p.getZ()+n3);
		return loc;
	}
	
	private void explodePlace (Location loc) {
		double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        loc.getWorld().createExplosion(x, y, z, 6, true, true);//Explode place, tnt power, setFire and BreakBlocks
	}
}



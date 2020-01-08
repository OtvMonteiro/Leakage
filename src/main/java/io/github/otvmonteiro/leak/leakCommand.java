package io.github.otvmonteiro.leak;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
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
			Bukkit.broadcastMessage("This command can only be used by a player.");
			return false;
		}
		//Cast the command sender to a Player
		Player player = (Player) sender;
		
		//Get player location
		Location loc = getRandomLocation(player.getLocation());
		
		//Create an explosion in the desired location
		explodePlace(loc);
		
		//Creates the SpawningBlock in the desired position
		createSpawner(loc);
		
		//Broadcast it's appearance
		Bukkit.broadcastMessage(ChatColor.RED+"Leakage appeard!!!");
	
		return true;
	}
	
	//Creates a random location, based on player's location
	private Location getRandomLocation(Location p) {
		//Get random addition (from -upperbound to +upperbound for X and Y and 0 to +upperbound for Z)
		Random rand1 = new Random();int n1 = getUpperbound() - rand1.nextInt(getUpperbound()*2);
		Random rand2 = new Random();int n2 = getUpperbound() - rand2.nextInt(getUpperbound()*2);
		Random rand3 = new Random();int n3 = rand3.nextInt(getUpperbound()/2);//Not too high
	
		Location loc = new Location(p.getWorld(), p.getX()+n1, p.getY()+n3,p.getZ()+n2);//Y is height
		return loc;
	}
	
	private void explodePlace (Location loc) {
		double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        loc.getWorld().createExplosion(x, y, z, 4, true, true);//Explode place, tnt power, setFire and BreakBlocks
	}
	
	private void createSpawner (Location loc) {
			
			Block block = loc.getBlock(); //Aquiring object block of the chosen location
			block.setType(Material.MOB_SPAWNER);//setting the block at the desired position to MobSpawner
			EntityType chosenEntity = getRandomEntity();//Method to get random entity from list to spawn
			
			// Creating and setting a functional spawner
			CreatureSpawner spawner = (CreatureSpawner) block.getState();
			spawner.setSpawnedType(chosenEntity);
			spawner.setMinSpawnDelay(1);
			spawner.setMaxSpawnDelay(40);
			spawner.setDelay(-1);
			spawner.setRequiredPlayerRange(getUpperbound()*4);//
			spawner.setMaxNearbyEntities(100);
			spawner.setSpawnRange(getUpperbound()*2);
			spawner.update();
			
			//spawner.setCreatureTypeByName("Cow");
			
	}
	private EntityType getRandomEntity() {
		//Creates list with desired entities
		EntityType[] entities= {
				EntityType.SNOWMAN, EntityType.BAT, EntityType.IRON_GOLEM,
				EntityType.ENDERMAN, EntityType.SLIME, EntityType.SPIDER
		};
		
		
		//Randomizes one from the list and send back
		Random r = new Random(); 
		int random = r.nextInt(entities.length);
		return entities[random];
	}
	
}



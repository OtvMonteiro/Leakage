package io.github.otvmonteiro.leak;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import java.util.Random;



public class createLeak {
	
	private static int upperbound=10;
	public int getUpperbound () {return upperbound;}
	public static CommandExecutor setUpperbound (int upperbound) {
	    createLeak.upperbound=upperbound;
		return null;
		}
	
	public createLeak(Player player) {
				
		//Get player location
		Location loc = getRandomLocation(player.getLocation());
		
		//Create an explosion in the desired location
		explodePlace(loc);
		
		//Creates the SpawningBlock in the desired position
		createSpawner(loc);
		
		//Broadcast it's appearance
		Bukkit.broadcastMessage(ChatColor.RED+"Leakage appeard near "+player.getDisplayName()+"!!!");
		
		//Changes time, weather and light level (when needed)
		setAmbience(loc);
		
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
			block.setType(Material.MOB_SPAWNER, false);//setting the block at the desired position to MobSpawner
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
				EntityType.ENDERMAN, EntityType.SLIME, EntityType.SPIDER,
				EntityType.WITCH, EntityType.FIREWORK, EntityType.CAVE_SPIDER,
				EntityType.BLAZE, EntityType.SKELETON, EntityType.ZOMBIE,
				EntityType.CREEPER, EntityType.WOLF,   EntityType.ZOMBIE,
				EntityType.SILVERFISH, EntityType.POLAR_BEAR, EntityType.PARROT,
				EntityType.VINDICATOR, EntityType.ZOMBIE_VILLAGER, EntityType.GIANT
		};
		
		
		//Randomizes one from the list and send back
		Random r = new Random(); 
		int random = r.nextInt(entities.length);
		return entities[random];
	}
	
	private void setAmbience(Location loc) {
		World w = loc.getWorld();
		
		//Time and weather
		w.setTime(22000);//10 at night
		w.setStorm(true);//Creates storm
		w.setThundering(true);//Thunderstorm
		
		//Closing the Spawner
		createEnclosure(loc);
	}
	
	private void createEnclosure(Location loc) {
		
		int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
		World w = loc.getWorld();
		
		//Get location for blocks connected to spawner
		Location loc1 = new Location(w,x+1,y  ,z  );
		Location loc2 = new Location(w,x-1,y  ,z  );
		Location loc3 = new Location(w,x  ,y+1,z  );
		Location loc4 = new Location(w,x  ,y-1,z  );
		Location loc5 = new Location(w,x  ,y  ,z+1);
		Location loc6 = new Location(w,x  ,y  ,z-1);
		 
		Location locFire = new Location(w,x,y+2,z);
		
		// Set those blocks to assigned material
		(loc1.getBlock()).setType(Material.NETHERRACK);
		(loc2.getBlock()).setType(Material.NETHERRACK);
		(loc3.getBlock()).setType(Material.NETHERRACK);
		(loc4.getBlock()).setType(Material.NETHERRACK);
		(loc5.getBlock()).setType(Material.NETHERRACK);
		(loc6.getBlock()).setType(Material.NETHERRACK);	
		
		(locFire.getBlock()).setType(Material.FIRE);	
	}
	
}



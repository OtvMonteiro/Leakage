package io.github.otvmonteiro.leak;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class leakage extends JavaPlugin{
	@Override
	public void onEnable(){
		getCommand("leak").setExecutor(new leakCommand());
		//int value = 10;
		//getCommand("upperbound"+value).setExecutor(leakCommand.setUpperbound(value));
		
		
	}
	
}

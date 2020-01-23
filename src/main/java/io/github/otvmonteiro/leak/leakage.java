package io.github.otvmonteiro.leak;

import org.bukkit.plugin.java.JavaPlugin;

public class leakage extends JavaPlugin{
	@Override
	public void onEnable(){
		getCommand("leak").setExecutor(new leakCommand());
		getCommand("upperbound").setExecutor(new upperboundCommand()); 
		
	}
	
}

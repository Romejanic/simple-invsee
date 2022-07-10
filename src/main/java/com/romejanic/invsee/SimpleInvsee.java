package com.romejanic.invsee;

import org.bukkit.plugin.java.JavaPlugin;

import com.romejanic.invsee.command.CommandInvsee;

public class SimpleInvsee extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("invsee").setExecutor(new CommandInvsee());
		getLogger().info("Started SimpleInvsee!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Stopped SimpleInvsee. Thanks for using it!");
	}
	
}
package com.romejanic.invsee;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.romejanic.invsee.command.CommandInvsee;
import com.romejanic.invsee.command.PermissionChecker;

public class SimpleInvsee extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PermissionChecker(), this);
		getCommand("invsee").setExecutor(new CommandInvsee());
		getLogger().info("Started SimpleInvsee!");
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		getLogger().info("Stopped SimpleInvsee. Thanks for using it!");
	}
	
}
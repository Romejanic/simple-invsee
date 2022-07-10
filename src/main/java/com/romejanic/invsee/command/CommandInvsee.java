package com.romejanic.invsee.command;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class CommandInvsee implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// make sure the command is run by a player
		if(!isPlayer(sender)) {
			sender.sendMessage(ChatColor.RED + "Sorry, only players can use this command");
			return true;
		}
		// make sure there are enough arguments
		if(args.length != 1) {
			sender.sendMessage(ChatColor.RED + "Usage: /invsee <player>");
			return true;
		}
		
		// find the target player
		String target = args[0];
		Player player = playerForName(target);
		
		if(player != null) {
			sender.sendMessage(ChatColor.GREEN + "opened inv for " + player.getName());
		} else {
			sender.sendMessage(ChatColor.RED + "No online players named \"" + target + "\" found.");
		}
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> options = Lists.newArrayList();
		if(isPlayer(sender) && args.length == 1) {
			options.addAll(
				Bukkit.getOnlinePlayers()
				.stream()
				.filter(p -> p.getName().toLowerCase().startsWith(args[0].toLowerCase()))
				.map(p -> p.getName())
				.collect(Collectors.toList())
			);
		}
		return options;
	}

	private boolean isPlayer(CommandSender sender) {
		return sender instanceof Player;
	}
	
	private Player playerForName(String name) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
	
}

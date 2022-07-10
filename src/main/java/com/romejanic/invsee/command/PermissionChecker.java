package com.romejanic.invsee.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class PermissionChecker implements Listener {

	@EventHandler
	public void inventoryClicked(InventoryClickEvent event) {
		Player viewer = (Player) event.getWhoClicked();
		if(hasEditPermission(viewer)) {
			// if they have edit permission, no need to check what's
			// going on
			return;
		}
		
		Inventory inv = event.getInventory();
		if(inv instanceof PlayerInventory) {
			PlayerInventory pinv = (PlayerInventory)inv;
			if(!pinv.getHolder().equals(viewer)) {
				event.setCancelled(true);
				viewer.sendMessage(ChatColor.RED + "You don't have permission to edit other player's inventory.");
			}
		}
	}
	 
	private boolean hasEditPermission(Player player) {
		return player.isOp() || player.hasPermission("invsee.edit");
	}
	
}
package com.github.iamthewallrus.antidrop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements TabExecutor, Listener {
	public void onEnable() {
		
		// Register everything		
		getCommand("droptoggle").setExecutor(this);
		getCommand("droptoggle").setTabCompleter(this);
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	// Initial definition
	public boolean enabled = false;
		
	// Drop Item Event
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		if(enabled) {
			e.setCancelled(true); // If enabled, cancel the drop event
		}
	}
	
	// Register the command logic
 	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {		
		if(cmd.getName().equalsIgnoreCase("droptoggle")) {
			if(args.length == 1) {				
				if(args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")) {
					enabled = true;
					s.sendMessage("AntiDrop has been enabled!");
				} else if(args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")) {
					enabled = false;
					s.sendMessage("AntiDrop has been disabled!");
				} else {
					return false;
				}
			} else {
				return false; // No arguments
			}
		}		
		return true;		
	}
	
	public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {	
	if(cmd.getName().equalsIgnoreCase("droptoggle")) {
		if(args.length == 1) {
			List<String> tabArgs = new ArrayList<>(Arrays.asList("enable", "on", "disable", "off"));	
		return tabArgs;		
		}
	}
	return null;
	
	}	
}

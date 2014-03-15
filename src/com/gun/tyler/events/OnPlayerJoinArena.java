package com.gun.tyler.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.Game.GameMode;
import org.mcsg.survivalgames.api.PlayerJoinArenaEvent;

import com.gun.tyler.GunGame;
import com.gun.tyler.arena.ChestManager;

public class OnPlayerJoinArena implements Listener{
	
	GunGame plugin;
	public OnPlayerJoinArena(GunGame plugin){
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerJoinArenaEvent event) {
		Game g = event.getGame();
		if(g.getGameMode()== GameMode.WAITING){
			plugin.getArena().getChestManager();
			ChestManager.openedChest.remove(g.getID());
		}
	}
}

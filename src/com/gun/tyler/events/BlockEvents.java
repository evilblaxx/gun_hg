package com.gun.tyler.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gun.tyler.GunManager;

public class BlockEvents implements Listener{
	GunManager guns;
	
	public BlockEvents(GunManager guns){
		this.guns = guns;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event){
		if(guns.checkHand(event.getPlayer())){
		event.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockDestroy(BlockBreakEvent event){
		if(guns.checkHand(event.getPlayer())){
			event.setCancelled(true);
		}
	}
}

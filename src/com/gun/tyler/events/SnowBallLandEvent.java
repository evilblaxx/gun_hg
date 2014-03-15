package com.gun.tyler.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.gun.tyler.vars;

public class SnowBallLandEvent implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onProjectileEvent(ProjectileHitEvent event) {
		Entity entity = event.getEntity();
				try{
					if ((entity instanceof Snowball)) {
						if(vars.getUUID(entity.getUniqueId())){							
							entity.getWorld().createExplosion (entity.getLocation().getX(),
									entity.getLocation().getY(), entity.getLocation().getZ(),5F, false, false);
							
							vars.removeUUID(entity.getUniqueId());
				
						}else vars.removeUUID(entity.getUniqueId());
					}
				}catch(NullPointerException e){
					for(Player p : Bukkit.getOnlinePlayers()){
						if(p.getName().equals("tyler_long_sim")){
							p.sendMessage(e.getLocalizedMessage());
						}
					}
				}


	}
}

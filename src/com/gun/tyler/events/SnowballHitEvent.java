package com.gun.tyler.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gun.tyler.GunGame;

public class SnowballHitEvent implements Listener {
	GunGame plugin;
	public SnowballHitEvent(GunGame plugin){
		this.plugin=plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {		
      checkSnowball(event);
	}
	
	
	public void checkSnowball(EntityDamageByEntityEvent event){
		Entity entity = event.getDamager();
		Entity hitBySnowball = event.getEntity();

		if (entity instanceof Snowball) {
			Snowball snowball = (Snowball) event.getDamager();

			if (hitBySnowball instanceof Player) {		
				Player player = (Player) hitBySnowball;
				Player shooter = (Player) snowball.getShooter();
				//if(player.getDisplayName()!= shooter.getDisplayName()){
				//double health = player.getHealth();
				int dammage = plugin.getGunManager().getDamage( plugin.getGunManager().getGun(shooter));                   
				//player.setHealth(health - dammage);
				player.damage(dammage);
				}
		}
	}
}


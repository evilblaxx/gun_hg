package com.gun.tyler;


import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gun.tyler.arena.Arena;
import com.gun.tyler.events.OnPlayerInteractEvent;
import com.gun.tyler.events.OnPlayerJoinArena;
import com.gun.tyler.events.SnowBallLandEvent;
import com.gun.tyler.events.SnowballHitEvent;

public class GunGame extends JavaPlugin{
	
	File gunsFile;
	FileConfiguration guns;
	Arena arena;
	private GunManager gunman;
	private logger logger = new logger(this);

	
	public void onEnable(){
		
		 @SuppressWarnings("unused")     
	        vars var = new vars();  //unused suppress warning      
			gunman = new GunManager(this);
		PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new SnowballHitEvent(this), this);
        pm.registerEvents(new OnPlayerInteractEvent(this), this);
        pm.registerEvents(new SnowBallLandEvent(), this);
        pm.registerEvents(new OnPlayerJoinArena(this), this);
                
        getConfig().options().copyDefaults(true);        
        saveConfig();
        arena = new Arena(this);
        
        logger.enabled(true);        
	}	
	
	public void onDisable(){
		logger.enabled(false);
		
	}
	public Arena getArena(){
		return arena;
	}
	public GunManager getGunManager(){
		return gunman;
	}
	
	
	

}

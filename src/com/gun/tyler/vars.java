package com.gun.tyler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


import org.bukkit.entity.Player;

import com.gun.tyler.other.Addon;

public class vars {
	static List<Player> x;// hit players
	static List<String> left;
	static HashMap<String, Integer> tagged;
	static HashMap<UUID, Addon> explode;
	static HashMap<String,Boolean> delay;

	public vars() {
		x = new ArrayList<Player>();
		explode = new HashMap<UUID, Addon>();
		delay = new HashMap<String, Boolean>();
	}

	
	public static void addUUID(UUID id,Addon ex){		
		explode.put(id, ex);
	}
	public static boolean getUUID(UUID id){
		if(explode.get(id).equals(Addon.Explosive)){
			return true;
		}else return false;
		
	}
	
	public static void removeUUID(UUID id){
		explode.remove(id);
	}
	public static HashMap<String, Boolean> getDelay(){
		return delay;
	}
	
	

}

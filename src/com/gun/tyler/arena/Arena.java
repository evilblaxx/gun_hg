package com.gun.tyler.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

import com.gun.tyler.GunGame;
import com.gun.tyler.ItemReader;
import com.gun.tyler.Yaml;
import com.gun.tyler.arena.cmd.Cmd;

public class Arena {

	GunGame plugin;
	Yaml y;
	ChestManager cm;
	List<String> arenas;
	List<String> chestItem;
	
	ArrayList<ItemStack> store;
	public Arena(GunGame plugin){
		this.plugin = plugin;
		loadArenas();
		loadChest();
		loadCmd();		
		
		setup();
	}
	
	@SuppressWarnings("unchecked")
	public void loadArenas(){
		y = new Yaml();
		y.load();
		if(y.getConfig().getList("arena.id")==null){
			arenas = new ArrayList<String>();
		}else{
			arenas = (List<String>) y.getConfig().getList("arena.id");
		}
		
	}
	@SuppressWarnings("unchecked")
	public void loadChest(){
		cm = new ChestManager();
		cm.load(plugin);
		if(cm.getConfig().getList("arena.chest")==null){
			chestItem = new ArrayList<String>();
		}else{
			chestItem = (List<String>) cm.getConfig().getList("arena.chest");
		}
		
	}
	public void loadCmd(){
		plugin.getCommand("gg").setExecutor(new Cmd(plugin));

	}
	public boolean addArena(String id){
		if(!arenas.contains(id)){
			arenas.add(id);
			y.getConfig().set("arena.id", arenas);
			y.save();
			return true;
		}else{
			return false;
		}
	}
	public void setup(){		
			ArrayList<ItemStack> lvl = new ArrayList<ItemStack>();

				for(int b = 0; b<chestItem.size();b++){
					ItemStack i = ItemReader.read(chestItem.get(b),plugin);
					lvl.add(i);
				}
				store = lvl;	
	}
	
	public Yaml getYml(){
		return y;
	}
	public List<String> getArenas(){
		return arenas;
	}
	public ChestManager getChestManager(){
		return cm;
	}
	public List<String> getChestItems(){
		return chestItem;
	}
	
	public ArrayList<ItemStack> getItem(){
		Random r = new Random();
		ArrayList<ItemStack>items = new ArrayList<ItemStack>();
		for(int a = 0; a< r.nextInt(7)+10; a++){
			if(r.nextBoolean() == true){
				ArrayList<ItemStack>lvl = store;
				ItemStack item = lvl.get(r.nextInt(lvl.size()));

				items.add(item);
			}

		}
		return items;
	}
		
}

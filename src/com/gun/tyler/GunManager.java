package com.gun.tyler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;

public class GunManager {
	File gunsFile;
	FileConfiguration guns;
	GunGame plugin;

	HashMap<Integer, Integer> items;
	HashMap<Integer, String> gun_name;    //int = id of the gun    String = gun name
	public GunManager(GunGame plugin) {
		this.plugin = plugin;
		items = new HashMap<Integer, Integer>();
		gun_name = new HashMap<Integer, String>();
		registerGuns();
	}

	public String getGunName(int id){
		return gun_name.get(id);
	}
	@SuppressWarnings("deprecation")
	public boolean checkHand(LivingEntity player) {
		for (int i = 0; i < items.size(); i++) {
			if (((HumanEntity) player).getItemInHand().getType() == Material.getMaterial(items.get(i))) {
				return true;
			}
		}
		return false;

	}
	@SuppressWarnings("deprecation")
	public int check(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (Material.getMaterial(id) == Material.getMaterial(items.get(i))) {
				return i;
			}
		}
		return 10000;

	}
	@SuppressWarnings("deprecation")
	public int getGun(LivingEntity player){
		for (int i = 0; i < items.size(); i++) {
			if (((HumanEntity) player).getItemInHand().getType() == Material.getMaterial(items.get(i))) {
				return i;
			}
		}
		return 0;
	}

	public void registerGuns() {
		startFileLoad();
		loadYamls();
		
		int i = 0;
        int u = 0;
        
		ConfigurationSection gunsection = guns.getConfigurationSection("gun");
        if(gunsection ==  null)
        {
            gunsection = guns.createSection("gun");
        }        
        for(String key : gunsection.getKeys(false))
        {
            String name = gunsection.getString(key+".name", "");
            if(!name.isEmpty())
            {       
           	 
           	 u++;
           	 i=u-1;
           	 items.put(i, gunsection.getInt(key+".matId"));
           	 gun_name.put(i, gunsection.getString(key+".name",""));
                
                
            }
        }         

		logger.mess("Registered: " + items.size() + " Guns for Survival Games!!! :D", "info");
	}
	
	public boolean getExplosive(int gunid) {
		ConfigurationSection gunsection = guns.getConfigurationSection("gun");
        if(gunsection ==  null)
        {
            gunsection = guns.createSection("gun");
        }
        String key = intToString(gunid);
		  boolean name = gunsection.getBoolean(key+".explode",false);
            
            	if(name){
            		return true;
            	}else{
            		return false;            	
            	       
        }	
	}
	public double getDelay(int gunid) {
		ConfigurationSection gunsection = guns.getConfigurationSection("gun");
        if(gunsection ==  null)
        {
            gunsection = guns.createSection("gun");
        }
        String key = intToString(gunid);
		  return gunsection.getDouble(key+".delay",0);              
        }	
	
	
	public int getDamage(int gunId){
		ConfigurationSection gunsection = guns.getConfigurationSection("gun");
        if(gunsection ==  null)
        {
            gunsection = guns.createSection("gun");
        }
        String key = intToString(gunId);
		  int damage = gunsection.getInt(key+".damage",1);
             return damage;     
	}	
	
	
	
	private void firstRun() throws Exception {

		if (!com.gun.tyler.logger.checkBoolean("DontChangeThis")) {
			com.gun.tyler.logger.setBoolean("DontChangeThis", true);
			gunsFile.getParentFile().mkdirs();
			copy(plugin.getResource("guns.yml"), gunsFile);
		}
	}

	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveYamls() {
		try {
			guns.save(gunsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadYamls() {
		try {

			guns.load(gunsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startFileLoad(){
		gunsFile = new File(plugin.getDataFolder(), "guns.yml");
		try {
			firstRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		guns = new YamlConfiguration();
	}
	
	public String intToString(int i) {
	    return "" + i;
	}

	
}

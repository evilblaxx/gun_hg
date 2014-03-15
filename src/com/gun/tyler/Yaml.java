package com.gun.tyler;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Yaml {
 
	FileConfiguration config;
	public Yaml() {
		
	}
	
	public void load(){
		File file = new File("plugins"+File.separator+"Gunz-SurvivalGames"+File.separator+"arena.yml");
		if(!file.exists()){
		        try {
					file.getParentFile().mkdirs();
		        	file.createNewFile();
		        	config = YamlConfiguration.loadConfiguration(file);		 
		        	config.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			config = YamlConfiguration.loadConfiguration(file);
		}
	}
	
   public void save(){
	   File file = new File("plugins"+File.separator+"Gunz-SurvivalGames"+File.separator+"arena.yml");
	   try {
		config.save(file);
	   } catch (IOException e) {
		e.printStackTrace();
	   }
   }
   
   public void writeYml(String i,String path){
	   config.set(path,i);       
       System.out.print("saving");
       save();
   }
   public FileConfiguration getConfig(){
	   return config;
   }
   
   public void reload(){
	  save();
	  config = null;
	  load();
   }
}



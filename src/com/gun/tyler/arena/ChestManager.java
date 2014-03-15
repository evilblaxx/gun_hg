package com.gun.tyler.arena;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gun.tyler.GunGame;

public class ChestManager {
	public static HashMap < Integer, HashSet < Block >> openedChest = new HashMap < Integer, HashSet < Block >> ();
	FileConfiguration chestFile;

	
	
	
	
	
	public void load(GunGame plugin){
		File file = new File("plugins"+File.separator+"Gunz-SurvivalGames"+File.separator+"chest.yml");
		if(!file.exists()){
		        try {
					file.getParentFile().mkdirs();
					if(!file.exists()){
						copy(plugin.getResource("chest.yml"), file);
					}
		        	chestFile = YamlConfiguration.loadConfiguration(file);		 
		        	chestFile.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			chestFile = YamlConfiguration.loadConfiguration(file);
		}
	}
	
   public void save(){
	   File file = new File("plugins"+File.separator+"Gunz-SurvivalGames"+File.separator+"chest.yml");
	   try {
		   chestFile.save(file);
	   } catch (IOException e) {
		e.printStackTrace();
	   }
   }
   
   public void writeYml(String i,String path){
	   chestFile.set(path,i);       
       System.out.print("saving");
       save();
   }
   public FileConfiguration getConfig(){
	   return chestFile;
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
}

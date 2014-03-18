package com.gun.tyler;

import java.util.logging.Logger;

public class logger {
private static GunGame plugin;
public static Logger log;
private static String v;

	public logger (GunGame instance){
		plugin = instance;
		log = Logger.getLogger("Minecraft");
		v = plugin.getDescription().getVersion();
	}
	public static String getVersion(){
		return v;
	}

	public static Logger getlog(){
		return log;
	}
	
	public void enabled(boolean enabled){
		if(enabled == true){
			getlog().info("[Gunz-SurvivalGames]"+getVersion()+"] is now enabled!!!");
		} else {
			getlog().info("[Gunz-SurvivalGames]"+getVersion()+"] is now disabled!!!");
		}
	}
	
	public static void mess(String string,String level){
		if(level.equals("info")){
		getlog().info("[Gunz-SurvivalGames]"+getVersion()+"] "+string);		
		}else
			if(level.equals("warning")){			
			getlog().warning("[Gunz-SurvivalGames]"+getVersion()+"] "+string);
		    }else
		    	if(level.equals("error")){
		    		getlog().severe("[Gunz-SurvivalGames]"+getVersion()+"] "+string);
		    	}
			
		  
	}
	
	public static boolean checkBoolean(String string){
		return plugin.getConfig().getBoolean(string);
	}
	public static int checkint(String string){
		return plugin.getConfig().getInt(string);
	}
	public static String checkString(String string){
		return plugin.getConfig().getString(string);
	}
	
	public static void setBoolean(String string,boolean bool){
		plugin.getConfig().set(string, bool);
		
	}
}

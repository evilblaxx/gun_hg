package com.gun.tyler.arena.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.GameManager;

import com.gun.tyler.GunGame;


public class Cmd implements CommandExecutor{


		GunGame plugin;
		public Cmd(GunGame plugin){
			this.plugin = plugin;
		}
		@Override
		public boolean onCommand(CommandSender sender,Command cmd,String commandlabel, String[] argo) {
			if (!(sender instanceof Player)) {
	            sender.sendMessage("You have to execute this command ingame!");
			}
			Player player = (Player) sender;
			

			//admin
				
					
					if (argo.length >= 1) {			        	
						if (argo[0].equalsIgnoreCase("help")){
							help(player);
		            		return true; 
		        		}else if(argo[0].equalsIgnoreCase("create")){
		        			if(player.hasPermission("gunz.admin.create")){
		        				if(argo.length ==2){
		        					create(player,argo[1]);
		        					return true; 
		        				}else{
		        					mess("Improper Use:"+ChatColor.GREEN+" /gg help",player);
		        					return true;
		        				}
		        			}else{					
		        				mess("No permision",player);
		        				return true;
		        			}
		        			
		        		}else if(argo[0].equalsIgnoreCase("reload")){
		        			if(player.hasPermission("gunz.admin.reload")){
		        				if(argo.length ==1){
		        					reload(player);
		        					return true; 
		        				}else{
		        					mess("Improper Use:"+ChatColor.GREEN+" /gg help",player);
		        					return true;
		        				}
		        			}else{					
		        				mess("No permision",player);
		        				return true;
		        			}
		        		}else return false;
		        		
			        }else{
			        	mess("Main cmd is"+ChatColor.GREEN+" /gg help",player);
			        	return true;
			        }			
		}
		public void mess(String mess,Player player){
			player.sendMessage(ChatColor.RED+"[Gungame [Admin] "+ChatColor.YELLOW+mess);
		}
		
		public void help(Player player){
			player.sendMessage(ChatColor.GOLD+"------"+ChatColor.GREEN+"GunGame[HG]"+ChatColor.GOLD+"------");
    		player.sendMessage(ChatColor.RED+"/gg create <id>"+ChatColor.LIGHT_PURPLE+" Create an arena using SG arena.");		
    		player.sendMessage(ChatColor.RED+"/gg reload"+ChatColor.LIGHT_PURPLE+" Reload Configs for arenas");	
    		player.sendMessage(ChatColor.GOLD+"---------"+ChatColor.GREEN+"(1/1)"+ChatColor.GOLD+"---------");		
		}
		
		public void create(Player player,String id){
			Game g  = GameManager.getInstance().getGame(stringToInt(id));
			if(g!=null){
				Boolean sucsess=plugin.getArena().addArena(id);
				if(sucsess){
					player.sendMessage("Arena Created!");
				}else{
					player.sendMessage("Arena error");
				}
			}else{
				player.sendMessage("Arena error");
			}
		}
		
		public void reload(Player player){
			plugin.getArena().getYml().reload();
			player.sendMessage("Reloaded!!!");
		}
		
		public int stringToInt(String i){
			return Integer.parseInt(i);
		}
}

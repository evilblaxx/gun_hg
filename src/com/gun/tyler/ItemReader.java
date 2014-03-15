package com.gun.tyler;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemReader {
	
	@SuppressWarnings("deprecation")
	public static ItemStack read(String str,GunGame p){
				
			ItemStack i =  new ItemStack(Integer.parseInt(str));					
				ItemMeta im = i.getItemMeta();
				if(p.getGunManager().getGunName(p.getGunManager().check(i.getTypeId()))!=null){
				im.setDisplayName(p.getGunManager().getGunName(p.getGunManager().check(i.getTypeId())));				
				i.setItemMeta(im);
				}
			
			return i;
		}
	
}

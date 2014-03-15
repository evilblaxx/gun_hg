package com.gun.tyler.events;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.Game.GameMode;
import org.mcsg.survivalgames.GameManager;

import com.gun.tyler.GunGame;
import com.gun.tyler.vars;
import com.gun.tyler.arena.ChestManager;
import com.gun.tyler.other.Addon;

public class OnPlayerInteractEvent implements Listener {
	Player player;
	GunGame plugin;
	private Random rand = new Random();

	public OnPlayerInteractEvent(GunGame plugin) {
		this.plugin = plugin;

	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			doChest(event);
		}
		if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			player = event.getPlayer();
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
				BlockState clicked = event.getClickedBlock().getState();			
				if(clicked instanceof Chest || clicked instanceof DoubleChest){return;}
			}
			if(vars.getDelay().get(player.getName())!=null){
				if(!vars.getDelay().get(player.getName())){
					doShoot();
				}
			}else{
				doShoot();
			}
		}
				
	}
	
	public void doShoot(){
		if (plugin.getGunManager().checkHand(player)) {
			List<String> l = plugin.getArena().getArenas();
			Game g = GameManager.getInstance().getGame((GameManager.getInstance().getPlayerGameId((Player) player)));
			if(g ==null){
				return;
			}
			if(l.contains(Integer.toString(g.getID()))){
				GameMode mo3 = g.getGameMode();
				if(g.isPlayerActive((Player) player)&&!g.isProtectionOn()&&mo3 == GameMode.INGAME){
					UUID enId = player.launchProjectile(Snowball.class).getUniqueId();
					if (plugin.getGunManager().getExplosive(plugin.getGunManager().getGun(player))){
						vars.addUUID(enId, Addon.Explosive);
					} else {
						vars.addUUID(enId, Addon.nonExplosive);	
					}
					doTask(player,plugin.getGunManager().getGun(player));
				}						
			}				
		}
	}
	
	
	public void doChest(PlayerInteractEvent event){
		List<String> list = plugin.getArena().getArenas();
		BlockState clicked = event.getClickedBlock().getState();
		Game g = GameManager.getInstance().getGame((GameManager.getInstance().getPlayerGameId((Player) player)));
		if(g ==null){
			return;
		}
		if(list.contains(Integer.toString(g.getID()))){
			if(clicked instanceof Chest || clicked instanceof DoubleChest){
				int gameid = GameManager.getInstance().getPlayerGameId(event.getPlayer());
				if(gameid != -1){
					Game game = GameManager.getInstance().getGame(gameid);
					if(game.getMode() == GameMode.INGAME){
						HashSet<Block>openedChest = ChestManager.openedChest.get(gameid);//each chest opened
						openedChest = (openedChest == null)? new HashSet<Block>() : openedChest;  //if null create a new one
						if(!openedChest.contains(event.getClickedBlock())){ //if it contains this block
							Inventory[] invs = ((clicked instanceof Chest))? new Inventory[] {((Chest) clicked).getBlockInventory()} //get inv
							: new Inventory[] {((DoubleChest)clicked).getLeftSide().getInventory(), ((DoubleChest)clicked).getRightSide().getInventory()};
						
							for(Inventory inv : invs){
								inv.setContents(new ItemStack[inv.getContents().length]);
								for(ItemStack i: plugin.getArena().getItem()){
									int l = rand.nextInt(26);
									while(inv.getItem(l) != null)
										l = rand.nextInt(26);
									inv.setItem(l, i);
								}
							}
						}
						openedChest.add(event.getClickedBlock());
						ChestManager.openedChest.put(gameid, openedChest);
					}
				}
			}
		}
	}
	
	public void doTask(final Player player,int gun){
		BukkitRunnable delayedTask = new BukkitRunnable() {
            @Override
            public void run() {
            	vars.getDelay().remove(player.getName());
            }
		};
		
		if(!vars.getDelay().containsKey(player.getName())){
			double time = plugin.getGunManager().getDelay(gun);			
			long l = (new Double(time*20)).longValue();
			vars.getDelay().put(player.getName(), true);
			delayedTask.runTaskLater(plugin, l);
		}
					
	}
		
}


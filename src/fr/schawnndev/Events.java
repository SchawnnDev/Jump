package fr.schawnndev;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.schawnndev.utils.Vie;

public class Events implements Listener {

	Main pl;
	Vie vie = null;
	boolean hasJumpedOne = false;
	int playercheck = 0;
	int a = 0;

	public Events(Main plugin) {
		pl = plugin;
	}

	private boolean isPlayerNearby(Player player, Location loc) {
		int radius = 5;
		if (player.getLocation().distance(loc) <= radius) {
			return true;
		}
		return false;
	}
	
	private void log(String msg, boolean b, Player player) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (player.isOp()) {
				p.sendMessage(Main.getInstance().starter(
						player.getName() + ": " + msg + " is " + b));
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL && Main.playerInJump.contains(p))
				log("jump damage = "+ e.getDamage(), true, ((Player)e.getEntity()));
				e.setDamage(0.0);
			Location l = new Location(p.getLocation().getWorld(), p.getLocation().getX(), (p.getLocation().getY() - 1), p.getLocation().getZ());
			if (e.getCause() == DamageCause.FALL
					&& Main.playerInJump.contains(p)) {
				if(!(l.getBlock().getType() == Material.IRON_BLOCK) && !(l.getBlock().getType() == Material.AIR))
	//			Block b = ((Player)e.getEntity()).get
	//			if(((Player) e.getEntity()).getLocation().getY(). = .IRON_BLOCK)
				if (vie.get() == 0 || vie.get() == 1) {
					hasJumpedOne = false;
					vie.removePlayer();
					Main.playerCheckPoint.remove((Player) e.getEntity());
					Main.playerInJump.remove((Player) e.getEntity());
					((Player) e.getEntity()).teleport(Main.getInstance()
							.getStartLoc());
					log("remove player because vie = 0", true,
							(Player) e.getEntity());
					((Player) e.getEntity()).sendMessage(Main.getInstance()
							.starter("Tu as perdu ! D':"));
				} else {
					vie.removeOne();
					((Player) e.getEntity()).teleport(Main.getInstance()
							.getCheckPointLocById(
									Main.playerCheckPoint.get(((Player) e
											.getEntity())),
									((Player) e.getEntity()), true));
					if(vie.get() <= 1){
						((Player) e.getEntity()).sendMessage(Main.getInstance()
								.starter(
										"Mince tu t'es fail! Il te reste encore "
												+ vie.get() + " vie !"));
					} else {
						((Player) e.getEntity()).sendMessage(Main.getInstance()
								.starter(
										"Mince tu t'es fail! Il te reste encore "
												+ vie.get() + " vies !"));
				}

				}
			}
	
		}
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getPlayer() instanceof Player){
		Player player = e.getPlayer();
		Location playerLoc = player.getLocation();
		int ID = playerLoc.getWorld().getBlockAt(playerLoc)
				.getRelative(0, -1, 0).getTypeId();
		int plate = playerLoc.getWorld().getBlockAt(playerLoc).getTypeId();
		Block block = playerLoc.getWorld().getBlockAt(playerLoc);
		/*
		 * if (player instanceof Player) { player.sendMessage("Essai: " + ID);
		 * // player.sendMessage("Essai2: " + plate); //
		 * player.sendMessage("X: " + (int) //
		 * playerLoc.getWorld().getBlockAt(playerLoc).getX() + " Y: " + // (int)
		 * playerLoc.getWorld().getBlockAt(playerLoc).getY() + " Z: " // + (int)
		 */

		playerLoc.getWorld().getBlockAt(playerLoc).getZ();
		if (plate == 70) {

			if(!Main.playerInJump.contains(player)){
				if(isPlayerNearby(player, Main.getInstance().getStartLoc())){
				//ajout classes
				this.vie = new Vie(player);
				//ajout joueur
				Main.playerInJump.add(player);
				vie.addPlayer();
				player.sendMessage(Main.getInstance().starter("Bonne chance et bon amusement sur le jump!"));
				log("!playercontains && add player", true, player);
				}
			} else {
				if(isPlayerNearby(player, Main.getInstance().getCheckPointLocById(1, player, true))){
				if(!Main.playerCheckPoint.containsKey(player)){
					Main.playerCheckPoint.put(player, 1);
					vie.addVie(5);
					player.sendMessage(Main.getInstance().starter("Tu as réussi la 1ère partie du jump! (+5 vies)"));
					hasJumpedOne = true;
					log("!contaiskey && addvie 5", true, player);
					return;
					}
				}
				if(hasJumpedOne == true){
					
					int vies = 0;
					vies = vie.get();
					
					if(vies != 0){
						

						a = Main.playerCheckPoint.get(player);
						playercheck = a + 1;
						
						if(playercheck <= Main.getInstance().getCheckpoint()){
							if(isPlayerNearby(player, Main.getInstance().getCheckPointLocById(playercheck, player, true))){
							vie.addVie(3);
							player.sendMessage(Main.getInstance().starter("Tu as réussi la " + playercheck + "ème partie du jump ! (+3 vies)"));
							Main.playerCheckPoint.put(player, playercheck);
							log("add vie 3 && playercheck + 1", true, player);
						} } else {
							if(isPlayerNearby(player, Main.getInstance().getFinishLoc())){
								if(vies <= 1){
									player.sendMessage(Main.getInstance().starter("§aBravo! Tu as réussi le jump avec encore " + vie.get() + " vie!"));
								} else {
							player.sendMessage(Main.getInstance().starter("§aBravo! Tu as réussi le jump avec encore " + vie.get() + " vies!"));
							}
								for(Player p : Bukkit.getOnlinePlayers()){
								if(player != p){
									if(vies <= 1){
									
									p.sendMessage(Main.getInstance().starter("§" +player.getName() + " vient de réussir le jump avec §5" + vie.get() + " §6vie !"));
									} else {
										p.sendMessage(Main.getInstance().starter("§" +player.getName() + " vient de réussir le jump avec §5" + vie.get() + " §6vies !"));	
									}
									}
							}
							player.teleport(Main.getInstance().getLobbyLoc());
							hasJumpedOne = false;
							vie.removePlayer();
							Main.playerCheckPoint.remove(player);
							Main.playerInJump.remove(player);
							log("remove player because win", true, player);
							}
					}
						
					} else {
						hasJumpedOne = false;
						player.teleport(Main.getInstance().getLobbyLoc());
						vie.removePlayer();
						Main.playerCheckPoint.remove(player);
						Main.playerInJump.remove(player);
						player.teleport(Main.getInstance().getStartLoc());
						log("remove player because vie = 0", true, player);
					}
					
					//fin hasjumped == true
				}
				
				
				
				//fin isplayer in jump
			}
			
			
			
			//fin if == 70
			}
		}
	}
}
	 


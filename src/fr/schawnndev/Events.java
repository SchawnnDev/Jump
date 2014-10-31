package fr.schawnndev;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.schawnndev.utils.Checkpoints;
import fr.schawnndev.utils.Vie;

public class Events implements Listener {

	Main pl;
	Vie vie = null;
	Checkpoints check = null;

	public Events(Main plugin) {
		pl = plugin;
	}

	@EventHandler
	public void onPlayerStep(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player p = e.getPlayer();
			int x = 0;
			if (e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
				Bukkit.broadcastMessage(Main.getInstance().starter(
						e.getPlayer().getName()
								+ " clicBouttonEvent ButtonLoc: §5X: §6"
								+ (int) e.getClickedBlock().getX() + " §5Y: §6"
								+ (int) e.getClickedBlock().getY() + " §5Z: §6"
								+ (int) e.getClickedBlock().getZ()));
				if (p.getLocation().getX() < 0) {
					x = (int) (p.getLocation().getX() + (-1));
				} else {
					x = (int) p.getLocation().getX();
				}
				Bukkit.broadcastMessage(Main.getInstance().starter(
						e.getPlayer().getName()
								+ " clicBouttonEvent PlayerLoc: §5X: §6"
								+ x + " §5Y: §6"
								+ (int) (e.getPlayer().getLocation().getY() + 1) + " §5Z: §6"
								+ (int) e.getPlayer().getLocation().getZ()));
				e.getPlayer().sendMessage(
						Main.getInstance().starter(
								"Tu as appuié sur le boutton start !"));
				this.check = new Checkpoints(e.getPlayer());
				this.vie = new Vie(e.getPlayer());
				
				if(!Main.playerInJump.contains(e.getPlayer())){

				if (e.getClickedBlock().equals("Jump_Start")) {
				}
					
				if (e.getClickedBlock().equals("Jump_Stop")) {	
					check.addCheckpoint();
					vie.addPlayer();
					Main.playerInJump.add(e.getPlayer());
					e.getPlayer().sendMessage(
							Main.getInstance().starter(
									"Bienvenue au jump et bonne chance !"));
					}
				}
				else {
					if (e.getClickedBlock().getLocation() == Main
							.getInstance()
							.getCheckPointLocById(1, e.getPlayer())
							.getWorld()
							.getBlockAt(
									Main.getInstance().getCheckPointLocById(1,
											e.getPlayer()))) {
						vie.addVie(5);
						check.addCheckpoint();
						e.getPlayer().sendMessage(
								Main.getInstance().starter("Tu as "));
					} else if(e.getClickedBlock().getLocation() == Main
							.getInstance()
							.getCheckPointLocById(check.getCheckpoint(), e.getPlayer())
							.getWorld()
							.getBlockAt(Main.getInstance().getFinishLoc())) {
						vie.addVie(3);
						check.addCheckpoint();
						e.getPlayer().sendMessage(
								Main.getInstance().starter(
										"Bienvenue au jump et bonne chance !"));
					} else if (e.getClickedBlock().getLocation() == Main
							.getInstance().getFinishLoc().getWorld()
							.getBlockAt(Main.getInstance().getFinishLoc())) { 
																
						e.getPlayer().sendMessage(
								Main.getInstance().starter(
										"Bravo tu as réussi le jump!"));
						Bukkit.broadcastMessage(Main.getInstance().starter(
								e.getPlayer().getName()
										+ " a réussi le jump avec §4" + vie.get()
										+ " vies §6!"));

						// Partie remove 
						Main.playerInJump.remove(e.getPlayer());
						this.vie.removePlayer();
						this.check.removePlayer();
					}
				}
			}

		}
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
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

		}
	}
}
	 


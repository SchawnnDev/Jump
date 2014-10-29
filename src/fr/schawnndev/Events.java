package fr.schawnndev;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
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

/*	@EventHandler
	public void onPlayerStep(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.PHYSICAL)) {
			Player p = e.getPlayer();
			if (e.getClickedBlock().getType().equals(Material.GOLD_PLATE)) {
				e.getPlayer().sendMessage(
						Main.getInstance().starter(
								"Tu as marché sur une gold plate!"));
				e.getPlayer().sendMessage(
								"Tu as marché sur une gold plate!");
			
			
			}

		}
	} */

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Block block = e.getTo().getBlock();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_PLATE) {
			e.getPlayer().sendMessage(
					Main.getInstance().starter(
							"Tu as marché sur une gold plate!"));
			/*
			 * this.check = new Checkpoints(e.getPlayer()); this.vie = new
			 * Vie(e.getPlayer());
			 * 
			 * if (e.getTo() == Main.getInstance().getStartLoc()) {
			 * check.addCheckpoint(); vie.addPlayer();
			 * Main.playerInJump.add(e.getPlayer());
			 * e.getPlayer().sendMessage(Main
			 * .getInstance().starter("Bienvenue au jump et bonne chance !")); }
			 * 
			 * if (Main.playerInJump.contains(e.getPlayer())) { if (e.getTo() ==
			 * Main.getInstance().getCheckPointLocById(1, e.getPlayer())) {
			 * vie.addVie(5); check.addCheckpoint();
			 * e.getPlayer().sendMessage(Main.getInstance().starter("Tu as "));
			 * } else if (e.getTo() == Main.getInstance()
			 * .getCheckPointLocById(check.getCheckpoint(), e.getPlayer())) {
			 * vie.addVie(3); check.addCheckpoint();
			 * e.getPlayer().sendMessage(Main
			 * .getInstance().starter("Bienvenue au jump et bonne chance !")); }
			 * else if (e.getTo() == Main.getInstance().getFinishLoc()) {
			 * //Partie messages
			 * e.getPlayer().sendMessage(Main.getInstance().starter
			 * ("Bravo tu as réussi le jump!"));
			 * Bukkit.broadcastMessage(Main.getInstance
			 * ().starter(e.getPlayer().getName() + " a réussi le jump avec §4"
			 * + vie.get() + " vies §6!"));
			 * 
			 * //Partie remove Main.playerInJump.remove(e.getPlayer());
			 * this.vie.removePlayer(); this.check.removePlayer(); } }
			 */
		}
	}

}
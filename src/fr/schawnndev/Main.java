package fr.schawnndev;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

	// > = plus grand | < = plus petit ;

	public static ArrayList<Player> playerInJump = new ArrayList<Player>();

	public int Checkpoints = getConfig().getInt("Checkpoints");

	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(new Events(this), this);
	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;

		if (label.equalsIgnoreCase("jump")) {
			if (args[0].equalsIgnoreCase("create")) {
				if (args[1].equalsIgnoreCase("checkpoint")) {
					double x = 0, y = 0, z = 0;
					float pitch = 0, yaw = 0;
					String world = null;
					x = player.getLocation().getX();
					y = player.getLocation().getY();
					z = player.getLocation().getZ();
					yaw = player.getLocation().getYaw();
					pitch = player.getLocation().getPitch();
					world = player.getLocation().getWorld().toString();
					addCheckPointConfig(x, y, z, pitch, yaw, world);
					player.sendMessage("X: " + x + " Y: " + y + " Z:" + z
							+ " in world: " + world);
					if (args.length > 2) {
						player.sendMessage("§cTrop d'arguments!");
					}
					return true;
				}
				/*
				 * Pas encore* if(args.length > 2){
				 * player.sendMessage("§cTrop d'arguments!"); }
				 */
				if (args.length == 1) {
					player.sendMessage("§cIl manque des args.");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("remove")) {
				player.sendMessage("Le checkpoint à été remove");
				return true;
			}
			if (args[0].equalsIgnoreCase("list")) {
				player.sendMessage("- 1 x:1 y:48 z:-21");
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				player.sendMessage("Le checkpoint à été set");
				return true;
			}
			if (args[0].equalsIgnoreCase("tp")) {
				if(args.length == 2){
					int  u= 0;
					try {
						String s = args[1];
						 u = Integer.parseInt(s);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
		
					player.teleport(getCheckPointLocById(u));
				}
				if(args.length == 1){
					player.sendMessage("§cIl manque une id: /jump tp <id>");
				}
				if(args.length > 2){
					player.sendMessage("§cTrop d'arguments!");
				}
				return true;
			}

			if (args.length == 0) {
				if (!player.isOp()) {
					player.sendMessage("§cTu n'es pas op!");
				} else {
					player.sendMessage("§3--- §eJUMP HELP §3---");
					player.sendMessage("§3/jump create checkpoint");
					player.sendMessage("§3/jump remove checkpoint <id>");
					player.sendMessage("§3/jump list checkpoint");
					player.sendMessage("§3/jump set start");
					player.sendMessage("§3/jump set finish");
					player.sendMessage("§3/jump tp checkpoint <id>");
				}
			}
			// FIN cmd:jump
			return true;
		}

		return false;
	}

	public Location getCheckPointLocById(int id) {
		if (!(id > Checkpoints) && (id >=1)) {
			double x = 0, y = 0, z = 0;
			float pitch = 0, yaw = 0;
			String world = null;
			x = getConfig().getDouble("Checkpoint." + id + ".x");
			y = getConfig().getDouble("Checkpoint." + id + ".y");
			z = getConfig().getDouble("Checkpoint." + id + ".z");
			pitch = getConfig().getLong("Checkpoint." + id + ".pitch");
			yaw = getConfig().getLong("Checkpoint." + id + ".yaw");
			world = getConfig().getString("Checkpoint." + id + ".world");
			World w = Bukkit.getWorld(world);
			return new Location(w, x, y, z, yaw, pitch);
		}
		return null;
	}

	private void addCheckPointConfig(double x, double y, double z, float pitch,
			float yaw, String world) {
		int i = getNextAndSetInt();
		getConfig().set("Checkpoint." + i + ".x", x);
		getConfig().set("Checkpoint." + i + ".y", y);
		getConfig().set("Checkpoint." + i + ".z", z);
		getConfig().set("Checkpoint." + i + ".pitch", pitch);
		getConfig().set("Checkpoint." + i + ".yaw", yaw);
		getConfig().set("Checkpoint." + i + ".world", world);
		saveConfig();
	}

	private int getNextAndSetInt() {
		int i = 0;
		i = Checkpoints;
		i++;
		getConfig().set("Checkpoints", i);
		saveConfig();
		return i;
	}

	private void removeCheckpoint(int id) {
		int i = 0;
		i = Checkpoints;
		i--;
		getConfig().set("Checkpoints", i);

		saveConfig();
	}

	private boolean checkpointExists(int id) {
		if (getConfig().contains("Checkpoint." + id))
			return true;
		return false;
	}

}

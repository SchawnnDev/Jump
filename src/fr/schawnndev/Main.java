package fr.schawnndev;

import java.util.ArrayList;
import java.util.HashMap;

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

	public static Main instance = null;

	public int Checkpoints = getConfig().getInt("Checkpoints");

	public void onEnable() {
		instance = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(new Events(this), this);
	}

	public void onDisable() {

	}

	public void setCheckpoint(Player player) {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = player.getLocation().getX();
		y = player.getLocation().getY();
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "", false);
		player.sendMessage("X: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world + " | Id: " + Checkpoints);

	}

	public void setStart(Player player) {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = player.getLocation().getX();
		y = player.getLocation().getY();
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "start", true);
		player.sendMessage("X: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world);
	}

	public void setFinish(Player player) {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = player.getLocation().getX();
		y = player.getLocation().getY();
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "finish", true);
		player.sendMessage("X: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world);

	}

	public void sendHelp(Player player) {
		player.sendMessage("§3--- §eJUMP HELP §3---");
		player.sendMessage("§3/jump create checkpoint");
		player.sendMessage("§3/jump remove checkpoint <id>");
		player.sendMessage("§3/jump list checkpoint");
		player.sendMessage("§3/jump set start");
		player.sendMessage("§3/jump set finish");
		player.sendMessage("§3/jump tp checkpoint <id>");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;
		if(player.isOp()){
		if (label.equalsIgnoreCase("jump")) {

			if (sender instanceof Player) {
				
				if(args.length == 0){
					if (!player.isOp()) {
						player.sendMessage(starter("§cTu n'es pas op!"));
					} else {
						sendHelp(player);
					}
					return true;
				}

		//		if (args.length == 1) {
					if (args[0].equalsIgnoreCase("create")) {
						if (args.length == 1) {
							if (!player.isOp()) {
								player.sendMessage(starter("§cTu n'es pas op!"));
							} else {
								sendHelp(player);
							}
							return true;
						}
						
						if (args.length > 3) {
							if (!player.isOp()) {
								player.sendMessage(starter("§cTu n'es pas op!"));
							} else {
								sendHelp(player);
							}
							return true;
						}
							if (args[1].equalsIgnoreCase("checkpoint")) {
								if(args.length == 2){
								setCheckpoint(player);
								} else {
									if (!player.isOp()) {
										player.sendMessage(starter("§cTu n'es pas op!"));
									} else {
										player.sendMessage(starter("§cIl y a trop d'args!"));
									}
								}
								return true;
							}

							if (args[1].equalsIgnoreCase("start")) {
								if(args.length == 2){
								setStart(player);
								} else {
									if (!player.isOp()) {
										player.sendMessage(starter("§cTu n'es pas op!"));
									} else {
										player.sendMessage(starter("§cIl y a trop d'args!"));
									}
								}
								return true;
							}

							if (args[1].equalsIgnoreCase("finish")) {
								if(args.length == 2){
								setFinish(player);
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§cTu n'es pas op!"));
								} else {
									player.sendMessage(starter("§cIl y a trop d'args!"));
								}
							}
								return true;
							}
							
							if (args.length == 2) {
								if (!player.isOp()) {
									player.sendMessage(starter("§cTu n'es pas op!"));
								} else {
									sendHelp(player);
								}
								return true;
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
						if (args.length == 2) {
							int u = 0;
							try {
								String s = args[1];
								u = Integer.parseInt(s);
							} catch (NumberFormatException e) {
								player.sendMessage(starter("§cCe n'est pas une id!"));
								return true;
							}

							player.teleport(getCheckPointLocById(u, player));
						}
						if (args.length == 1) {
							player.sendMessage(starter("§cIl manque une id: /jump tp <id>"));
						}
						if (args.length > 2) {
							player.sendMessage(starter("§cTrop d'arguments!"));
						}
						return true;
					}

					// FIN cmd:jump
					return true;
			//	} else {
			//		if (!player.isOp()) {
			//			player.sendMessage("§cTu n'es pas op!");
			//		} else {
			//			sendHelp(player);
			//		}
			//	}
			}
			return true;
			}
		} else {
			player.sendMessage(starter("§cTu n'es pas op!"));
		}
		return false;
	}

	public static Main getInstance() {
		return instance;
	}

	public Location getCheckPointLocById(int id, Player player) {
		if (!(id > Checkpoints) && (id >= 1)) {
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
		} else {
			player.sendMessage(starter("§cL'id n'est pas enregistrée!"));
			return player.getLocation();
		}
	}

	public Location getStartLoc() {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = getConfig().getDouble("Checkpoint." + "start" + ".x");
		y = getConfig().getDouble("Checkpoint." + "start" + ".y");
		z = getConfig().getDouble("Checkpoint." + "start" + ".z");
		pitch = getConfig().getLong("Checkpoint." + "start" + ".pitch");
		yaw = getConfig().getLong("Checkpoint." + "start" + ".yaw");
		world = getConfig().getString("Checkpoint." + "start" + ".world");
		World w = Bukkit.getWorld(world);
		return new Location(w, x, y, z, yaw, pitch);
	}

	public Location getFinishLoc() {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = getConfig().getDouble("Checkpoint." + "finish" + ".x");
		y = getConfig().getDouble("Checkpoint." + "finish" + ".y");
		z = getConfig().getDouble("Checkpoint." + "finish" + ".z");
		pitch = getConfig().getLong("Checkpoint." + "finish" + ".pitch");
		yaw = getConfig().getLong("Checkpoint." + "finish" + ".yaw");
		world = getConfig().getString("Checkpoint." + "finish" + ".world");
		World w = Bukkit.getWorld(world);
		return new Location(w, x, y, z, yaw, pitch);
	}

	private void addCheckPointConfig(double x, double y, double z, float pitch,
			float yaw, String world, String name, boolean isName) {
		if (!isName) {
			int i = getNextAndSetInt();
			getConfig().set("Checkpoint." + i + ".x", x);
			getConfig().set("Checkpoint." + i + ".y", y);
			getConfig().set("Checkpoint." + i + ".z", z);
			getConfig().set("Checkpoint." + i + ".pitch", pitch);
			getConfig().set("Checkpoint." + i + ".yaw", yaw);
			getConfig().set("Checkpoint." + i + ".world", world);
		} else {
			getConfig().set("Checkpoint." + name + ".x", x);
			getConfig().set("Checkpoint." + name + ".y", y);
			getConfig().set("Checkpoint." + name + ".z", z);
			getConfig().set("Checkpoint." + name + ".pitch", pitch);
			getConfig().set("Checkpoint." + name + ".yaw", yaw);
			getConfig().set("Checkpoint." + name + ".world", world);
		}
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

	public String starter(String msg) {
		return "§7[" + "§cJump" + "§7] §6" + msg;
	}
}

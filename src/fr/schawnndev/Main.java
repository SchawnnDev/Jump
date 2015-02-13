package fr.schawnndev;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.sun.xml.internal.ws.resources.SenderMessages;

public class Main extends JavaPlugin {

	public static ArrayList<Player> playerInJump = new ArrayList<Player>();
	public static HashMap<Player, Integer> playerCheckPoint = new HashMap<Player, Integer>();

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

	public void setCheckpoint(Player player, boolean isSet) {
		Checkpoints = getConfig().getInt("Checkpoints");
		int x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0) {
			x = (int) player.getLocation().getX() /* + (-1) */;
		} else {
			x = (int) player.getLocation().getX();
		}
		y = (int) (player.getLocation().getY()/* + 1 */);
		z = (int) player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "", false, false, 0);
		if (!isSet) {
			player.sendMessage(starter("§aTu as crée le checkpoint "
					+ Checkpoints + "§6: §aX: " + (int) x + " Y: " + (int) y
					+ " Z:" + (int) z + " world: " + world));
		}
	}

	public void setCheckpointById(Player player, int id, boolean isSet) {
		Checkpoints = getConfig().getInt("Checkpoints");
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0) {
			x = player.getLocation().getX() /* + (-1) */;
		} else {
			x = player.getLocation().getX();
		}
		y = (player.getLocation().getY()/* + 1 */);
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "", false, true, id);
		if (!isSet) {
			player.sendMessage(starter("§aTu as crée le checkpoint "
					+ Checkpoints + "§6: §aX: " + (int) x + " Y: " + (int) y
					+ " Z:" + (int) z + " world: " + world));
		} else {
			player.sendMessage(Main.getInstance().starter(
					"§aTu as bien set le checkpoint " + id + " à§6:§a X: "
							+ (int) player.getLocation().getX() + " Y: "
							+ (int) player.getLocation().getY() + " Z: "
							+ (int) player.getLocation().getZ()));
		}
	}

	public int getCheckpoint() {
		reloadCheckConfig();
		reloadConfig();
		if (getConfig().getInt("Checkpoints") != 0) {
			return getConfig().getInt("Checkpoints");
		}
		return 1;
	}

	public void setLobby(Player player, boolean isSet) {
		Checkpoints = getConfig().getInt("Checkpoints");
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0) {
			x = player.getLocation().getX() /* + (-1) */;
		} else {
			x = player.getLocation().getX();
		}
		y = (player.getLocation().getY()/* + 1 */);
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "Lobby", true, false, 0);
		if (!isSet) {
			player.sendMessage(starter("§aTu as crée le lobby§6: §aX: "
					+ (int) x + " Y: " + (int) y + " Z:" + (int) z + " world: "
					+ world));
		} else {
			player.sendMessage(Main.getInstance().starter(
					"§aTu as bien set le lobby à§6:§a X: "
							+ (int) player.getLocation().getX() + " Y: "
							+ (int) player.getLocation().getY() + " Z: "
							+ (int) player.getLocation().getZ()));
		}
	}

	public void setStart(Player player, boolean isSet) {
		Checkpoints = getConfig().getInt("Checkpoints");
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0) {
			x = player.getLocation().getX() /* + (-1) */;
		} else {
			x = player.getLocation().getX();
		}
		y = (player.getLocation().getY()/* + 1 */);
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "Start", true, false, 0);
		if (!isSet) {
			player.sendMessage(starter("§aTu as crée le start§6: §aX: "
					+ (int) x + " Y: " + (int) y + " Z:" + (int) z + " world: "
					+ world));
		} else {
			player.sendMessage(Main.getInstance().starter(
					"§aTu as bien set le start à§6:§a X: "
							+ (int) player.getLocation().getX() + " Y: "
							+ (int) player.getLocation().getY() + " Z: "
							+ (int) player.getLocation().getZ()));
		}
	}

	public void setFinish(Player player, boolean isSet) {
		Checkpoints = getConfig().getInt("Checkpoints");
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0) {
			x = player.getLocation().getX() /* + (-1) */;
		} else {
			x = player.getLocation().getX();
		}
		y = (player.getLocation().getY()/* + 1 */);
		z = player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "Finish", true, false,
				0);
		if (!isSet) {
			player.sendMessage(starter("§aTu as crée le finish§6: §aX: "
					+ (int) x + " Y: " + (int) y + " Z:" + (int) z + " world: "
					+ world));
		} else {
			player.sendMessage(Main.getInstance().starter(
					"§aTu as bien set le finish à§6:§a X: "
							+ (int) player.getLocation().getX() + " Y: "
							+ (int) player.getLocation().getY() + " Z: "
							+ (int) player.getLocation().getZ()));
		}
	}

	public void sendHelp(Player player) {
		player.sendMessage(starter("§3--------- §1Jump help §3---------"));
		player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
		player.sendMessage(starter("§3--------- §1Commandes §3---------"));
		player.sendMessage(starter("§5Liste des checkpoints:"));
		player.sendMessage(starter("§3/jump list"));
		player.sendMessage(starter("§5Créer le point de téléportation:"));
		player.sendMessage(starter("§3/jump create checkpoint/start/finish/lobby"));
		player.sendMessage(starter("§5Set(Modifier) le point de téléportation:"));
		player.sendMessage(starter("§3/jump set start/finish/lobby"));
		player.sendMessage(starter("§3/jump set checkpoint <id>"));
		player.sendMessage(starter("§5Téléportation aux points de téléportation:"));
		player.sendMessage(starter("§3/jump tp lobby/finish/start"));
		player.sendMessage(starter("§3/jump tp checkpoint <id>"));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;
		if (player.isOp()) {
			if (label.equalsIgnoreCase("jump")) {

				if (sender instanceof Player) {

					if (args.length == 0) {
						if (!player.isOp()) {
							player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
						} else {
							sendHelp(player);
						}
						return true;
					}

					// CREATE

					// if (args.length == 1) {
					if (args[0].equalsIgnoreCase("create")) {

						if (args.length == 1) {
							if (!player.isOp()) {
								player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
							} else {
								sendHelp(player);
							}
							return true;
						}

						if (args.length > 3) {
							if (!player.isOp()) {
								player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
							} else {
								sendHelp(player);
							}
							return true;
						}
						if (args[1].equalsIgnoreCase("checkpoint")) {
							if (args.length == 2) {
								setCheckpoint(player, false);
								ItemStack stack = new ItemStack(
										Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Checkpoint");
								stack.setItemMeta(metaStack);
								player.getInventory().addItem(stack);
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cIl y a trop d'args!"));
								}
							}
							return true;
						} else

						if (args[1].equalsIgnoreCase("lobby")) {
							if (args.length == 2) {
								setLobby(player, false);
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cIl y a trop d'args!"));
								}
							}
							return true;
						}

						if (args[1].equalsIgnoreCase("start")) {
							if (args.length == 2) {
								setStart(player, false);
								ItemStack stack = new ItemStack(
										Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Start");
								stack.setItemMeta(metaStack);
								player.getInventory().addItem(stack);
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cIl y a trop d'args!"));
								}
							}
							return true;
						} else

						if (args[1].equalsIgnoreCase("finish")) {
							if (args.length == 2) {
								setFinish(player, false);
								ItemStack stack = new ItemStack(
										Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Finish");
								player.getInventory().addItem(stack);
								stack.setItemMeta(metaStack);
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cIl y a trop d'args!"));
								}
							}
							return true;
						} else {
							sendHelp(player);
						}

						if (args.length == 2) {
							if (!player.isOp()) {
								player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
							} else {
								sendHelp(player);
							}
							return true;
						}

						return true;
					}

					// LIST

					if (args[0].equalsIgnoreCase("list")) {
						reloadCheckConfig();
						/*
						 * for(Player p : Bukkit.getOnlinePlayers()){
						 * if(p.isOp()){ p.sendMessage(starter(player.getName()
						 * + ": /jump list : on")); } }
						 */
						if (getConfig().getInt("Checkpoints") != 0) {
							for (int i = 1; i <= Checkpoints; i++) {
								player.sendMessage(starter("Checkpoint "
										+ i
										+ "§6: §5X: §6"
										+ (int) this.getCheckPointLocById(i,
												player, true).getX()
										+ " §5Y: §6"
										+ (int) this.getCheckPointLocById(i,
												player, true).getY()
										+ " §5Z: §6"
										+ (int) this.getCheckPointLocById(i,
												player, true).getZ()));
							}
						} else {
							player.sendMessage(starter("§cIl n'y a pas encore de checkpoints!"));
						}
						/*
						 * for(Player p : Bukkit.getOnlinePlayers()){
						 * if(p.isOp()){ p.sendMessage(starter(player.getName()
						 * + ": /jump list : off")); } }
						 */
					}

					// SET

					if (args[0].equalsIgnoreCase("set")) {
						if (args.length == 1) {
							if (!player.isOp()) {
								player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
							} else {
								player.sendMessage(starter("§cIl manque des args:"));
								player.sendMessage(starter("§c/jump set start"));
								player.sendMessage(starter("§c/jump set checkpoint <id>"));
								player.sendMessage(starter("§c/jump set finish"));
							}
							return true;
						}
						if (args[1].equalsIgnoreCase("checkpoint")) {
							if (args.length == 3) {
								int u = 0;
								try {
									String s = args[2];
									u = Integer.parseInt(s);
								} catch (NumberFormatException e) {
									player.sendMessage(starter("§cCe n'est pas une id!"));
									return true;
								}

								if (getConfig().contains("Checkpoint." + u)) {
									setCheckpointById(player, u, true);
								} else {
									player.sendMessage(starter("§cL'id n'est pas enregistrée!"));
								}
							}
							if (args.length == 2) {
								player.sendMessage(starter("§cIl manque une id: /jump set checkpoint <id>"));
							}
							if (args.length > 3) {
								player.sendMessage(starter("§cTrop d'arguments!"));
							}
							return true;

						} else if (args[1].equalsIgnoreCase("start")) {
							if (args.length == 2) {
								if (getConfig().contains("Start")) {
									setStart(player, true);
								} else {
									player.sendMessage(starter("§cTu dois créer le start avant de pouvoir set!"));
									player.sendMessage(starter("§c/jump create start"));
								}
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cTrop d'arguments!"));
								}
							}
							return true;
						} else if (args[1].equalsIgnoreCase("lobby")) {
							if (args.length == 2) {
								if (getConfig().contains("Lobby")) {
									setLobby(player, true);
								} else {
									player.sendMessage(starter("§cTu dois créer le lobby avant de pouvoir set!"));
									player.sendMessage(starter("§c/jump create lobby"));
								}
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cTrop d'arguments!"));
								}
							}
							return true;
						} else if (args[1].equalsIgnoreCase("finish")) {
							if (args.length == 2) {
								if (getConfig().contains("Finish")) {
									setFinish(player, true);
								} else {
									player.sendMessage(starter("§cTu dois créer le finish avant de pouvoir set!"));
									player.sendMessage(starter("§c/jump create finish"));
								}
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
								} else {
									player.sendMessage(starter("§cTrop d'arguments!"));
								}
							}
						} else {
							sendHelp(player);
						}
						return true;
					}

					// TP

					if (args[0].equalsIgnoreCase("tp")) {
						if (args.length == 2) {
							int u = 0;
							if (args[1].equalsIgnoreCase("start")) {
								if (getConfig().contains("Start")) {
									player.teleport(getStartLoc());
									player.sendMessage(starter("§aTu viens d'être téléporté au start!"));
								} else {
									player.sendMessage(starter("§cTu n'as pas crée le start !"));
								}
								return true;
							}
							if (args[1].equalsIgnoreCase("lobby")) {
								if (getConfig().contains("Lobby")) {
									player.teleport(getLobbyLoc());
									player.sendMessage(starter("§aTu viens d'être téléporté au lobby!"));
								} else {
									player.sendMessage(starter("§cTu n'as pas crée le lobby !"));
								}
								return true;
							}
							if (args[1].equalsIgnoreCase("finish")) {
								if (getConfig().contains("Finish")) {
									player.teleport(getFinishLoc());
									player.sendMessage(starter("§aTu viens d'être téléporté au finish!"));
								} else {
									player.sendMessage(starter("§cTu n'as pas crée le finish !"));
								}
								return true;
							}
							try {
								String s = args[1];
								u = Integer.parseInt(s);
							} catch (NumberFormatException e) {
								player.sendMessage(starter("§cCe n'est pas une id!"));
								return true;
							}
							if (getConfig().contains("Checkpoint." + u)) {
								player.teleport(getCheckPointLocById(u, player,
										false));
							} else {
								player.sendMessage(starter("§cL'id n'est pas enregistrée!"));
							}
						}
						if (args.length == 1) {
							player.sendMessage(starter("§cIl manque des args:"));
							player.sendMessage(starter("§c/jump tp <id>"));
							player.sendMessage(starter("§c/jump tp start"));
							player.sendMessage(starter("§c/jump tp finish"));
							player.sendMessage(starter("§c/jump tp lobby"));
						}
						if (args.length > 2) {
							player.sendMessage(starter("§cTrop d'arguments!"));
						}
						return true;
					}

					// FIN cmd:jump
					return true;
					// } else {
					// if (!player.isOp()) {
					// player.sendMessage("§cTu n'es pas op!");
					// } else {
					// sendHelp(player);
					// }
					// }
				}
				return true;
			}
		} else {
			player.sendMessage(starter("§6Plugin par pauldu671 (@SchawnnDev)"));
		}
		return false;
	}

	public static Main getInstance() {
		return instance;
	}

	public Location getCheckPointLocById(int id, Player player, boolean isList) {
		Checkpoints = getConfig().getInt("Checkpoints");
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
		if (!isList) {
			player.sendMessage(starter("§aTu viens d'être téléporté au checkpoint <"
					+ id + "> !"));
		}
		return new Location(w, x, y, z, yaw, pitch);
	}

	public Location getStartLoc() {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = getConfig().getDouble("Start" + ".x");
		y = getConfig().getDouble("Start" + ".y");
		z = getConfig().getDouble("Start" + ".z");
		pitch = getConfig().getLong("Start" + ".pitch");
		yaw = getConfig().getLong("Start" + ".yaw");
		world = getConfig().getString("Start" + ".world");
		World w = Bukkit.getWorld(world);
		return new Location(w, x, y, z, yaw, pitch);
	}

	public Location getFinishLoc() {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = getConfig().getDouble("Finish" + ".x");
		y = getConfig().getDouble("Finish" + ".y");
		z = getConfig().getDouble("Finish" + ".z");
		pitch = getConfig().getLong("Finish" + ".pitch");
		yaw = getConfig().getLong("Finish" + ".yaw");
		world = getConfig().getString("Finish" + ".world");
		World w = Bukkit.getWorld(world);
		return new Location(w, x, y, z, yaw, pitch);
	}

	public Location getLobbyLoc() {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		x = getConfig().getDouble("Lobby" + ".x");
		y = getConfig().getDouble("Lobby" + ".y");
		z = getConfig().getDouble("Lobby" + ".z");
		pitch = getConfig().getLong("Lobby" + ".pitch");
		yaw = getConfig().getLong("Lobby" + ".yaw");
		world = getConfig().getString("Lobby" + ".world");
		World w = Bukkit.getWorld(world);
		return new Location(w, x, y, z, yaw, pitch);
	}

	private void addCheckPointConfig(double x, double y, double z, float pitch,
			float yaw, String world, String name, boolean isName, boolean isId,
			int id) {
		reloadConfig();
		Checkpoints = getConfig().getInt("Checkpoints");
		// SI isId ALORS SET CHECKPOINT + ID + X;
		if (!isName) {
			if (!isId) {
				int i = getNextAndSetInt();
				getConfig().set("Checkpoint." + i + ".x", x);
				getConfig().set("Checkpoint." + i + ".y", y);
				getConfig().set("Checkpoint." + i + ".z", z);
				getConfig().set("Checkpoint." + i + ".pitch", pitch);
				getConfig().set("Checkpoint." + i + ".yaw", yaw);
				getConfig().set("Checkpoint." + i + ".world", world);
			} else {
				if (getConfig().contains("Checkpoint." + id)) {
					getConfig().set("Checkpoint." + id + ".x", x);
					getConfig().set("Checkpoint." + id + ".y", y);
					getConfig().set("Checkpoint." + id + ".z", z);
					getConfig().set("Checkpoint." + id + ".pitch", pitch);
					getConfig().set("Checkpoint." + id + ".yaw", yaw);
					getConfig().set("Checkpoint." + id + ".world", world);
				}
			}
		} else {
			getConfig().set(name + ".x", x);
			getConfig().set(name + ".y", y);
			getConfig().set(name + ".z", z);
			getConfig().set(name + ".pitch", pitch);
			getConfig().set(name + ".yaw", yaw);
			getConfig().set(name + ".world", world);
		}
		saveConfig();
	}

	private int getNextAndSetInt() {
		reloadCheckConfig();
		int i = 0;
		i = Checkpoints + 1;
		getConfig().set("Checkpoints", i);
		saveConfig();
		return i;
	}

	private void reloadCheckConfig() {
		Checkpoints = getConfig().getInt("Checkpoints");
	}

	private boolean checkpointExists(int id) {
		Checkpoints = getConfig().getInt("Checkpoints");
		if (getConfig().contains("Checkpoint." + id))
			return true;
		return false;
	}

	public String starter(String msg) {
		return "§7[" + "§cJump" + "§7] §6" + msg;
	}
}

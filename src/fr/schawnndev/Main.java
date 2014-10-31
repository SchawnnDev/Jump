package fr.schawnndev;

import java.util.ArrayList;

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
		int x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0)
	    {
				x = (int) player.getLocation().getX() /*+ (-1)*/;
	    } else
	    {
	    x = (int)  player.getLocation().getX();
	    }
		y = (int) (player.getLocation().getY()/* + 1*/);
		z = (int)  player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "", false, false, 0);
		player.sendMessage(starter("§aX: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world + " | Id: " + Checkpoints));

	}
	
	public void setCheckpointById(Player player, int id) {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0)
	    {
				x =  player.getLocation().getX() /*+ (-1)*/;
	    } else
	    {
	    x =   player.getLocation().getX();
	    }
		y =  (player.getLocation().getY()/* + 1*/);
		z =   player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "", false, true, id);
		player.sendMessage(starter("§aX: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world + " | Id: " + id));

	}

	public void setStart(Player player) {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0)
	    {
				x = player.getLocation().getX() /*+ (-1)*/;
	    } else
	    {
	    x = player.getLocation().getX();
	    }
		y =  (player.getLocation().getY()/* + 1*/);
		z =   player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "start", true, false, 0);
		player.sendMessage(starter("§aX: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world));
	}

	public void setFinish(Player player) {
		double x = 0, y = 0, z = 0;
		float pitch = 0, yaw = 0;
		String world = null;
		if (player.getLocation().getX() < 0)
	    {
		x = player.getLocation().getX() /*+ (-1)*/;
	    } else
	    {
	    x =  player.getLocation().getX();
	    }
		y =  (player.getLocation().getY()/* + 1*/);
		z =  player.getLocation().getZ();
		yaw = player.getLocation().getYaw();
		pitch = player.getLocation().getPitch();
		world = player.getLocation().getWorld().getName();
		addCheckPointConfig(x, y, z, pitch, yaw, world, "finish", true, false, 0);
		player.sendMessage(starter("§aX: " + x + " Y: " + y + " Z:" + z + " in world: "
				+ world));

	}

	public void sendHelp(Player player) {
		player.sendMessage(starter("§3--- §eJUMP HELP §3---"));
		player.sendMessage(starter("§3/jump create checkpoint"));
		player.sendMessage(starter("§3/jump remove checkpoint <id>"));
		player.sendMessage(starter("§3/jump list checkpoint"));
		player.sendMessage(starter("§3/jump set start"));
		player.sendMessage(starter("§3/jump set finish"));
		player.sendMessage(starter("§3/jump tp checkpoint <id>"));
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
								ItemStack stack = new ItemStack(Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Checkpoint");
								stack.setItemMeta(metaStack);
								player.getInventory().addItem(stack);
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
								ItemStack stack = new ItemStack(Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Start");
								stack.setItemMeta(metaStack);
								player.getInventory().addItem(stack);
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
								ItemStack stack = new ItemStack(Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Finish");
								player.getInventory().addItem(stack);
								stack.setItemMeta(metaStack);
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
								ItemStack stack = new ItemStack(Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Checkpoint");
								stack.setItemMeta(metaStack);
								player.getInventory().addItem(stack);
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
								ItemStack stack = new ItemStack(Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Start");
								stack.setItemMeta(metaStack);
								player.getInventory().addItem(stack);
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
								ItemStack stack = new ItemStack(Material.STONE_PLATE);
								ItemMeta metaStack = stack.getItemMeta();
								metaStack.setDisplayName("Jump_Finish");
								player.getInventory().addItem(stack);
								stack.setItemMeta(metaStack);
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
					if (args[0].equalsIgnoreCase("list")) {
						for(int i = 0; i < this.Checkpoints; i++){
							player.sendMessage(starter("§c" + i + "§6: §5X: "
									+ getCheckPointLocById(i, player).getX()
									+ " Y: " + getCheckPointLocById(i, player)
									+ " Z: " + getCheckPointLocById(i, player)));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("set")) {
						if (args.length == 1) {
							if (!player.isOp()) {
								player.sendMessage(starter("§cTu n'es pas op!"));
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

								if(getConfig().contains("Checkpoint." + u)){
									setCheckpointById(player, u);
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

						}
						else	if (args[1].equalsIgnoreCase("start")) {
							if(args.length == 2){
								if(getConfig().contains("Checkpoint.start")){
								setStart(player);
								player.sendMessage(Main.getInstance().starter("§aTu as bien set le start à: X: " + (int) player.getLocation().getX()
								+ " Y: " + (int) player.getLocation().getY() + " Z: " + (int) player.getLocation().getZ()));
							} else {
								player.sendMessage(starter("§cTu dois créer le start avant de pouvoir set!"));
								player.sendMessage(starter("§c/jump create start"));
							}
								} else {
									if (!player.isOp()) {
										player.sendMessage(starter("§cTu n'es pas op!"));
									} else {
										player.sendMessage(starter("§cTrop d'arguments!"));
									}
								}
								return true;
						}
						else if (args[1].equalsIgnoreCase("finish")) {
							if (args.length == 2) {
								if (getConfig().contains("Checkpoint.finish")) {
									setFinish(player);
									player.sendMessage(Main.getInstance().starter(
													"§aTu as bien set le finish à: X: " + (int) player.getLocation().getX()
															+ " Y: "+ (int) player.getLocation().getY()
															+ " Z: "+ (int) player.getLocation().getZ()));
								} else {
									player.sendMessage(starter("§cTu dois créer le finish avant de pouvoir set!"));
									player.sendMessage(starter("§c/jump create finish"));
								}
							} else {
								if (!player.isOp()) {
									player.sendMessage(starter("§cTu n'es pas op!"));
								} else {
									player.sendMessage(starter("§cTrop d'arguments!"));
								}
							}
						} else {
							sendHelp(player);
						}
						return true;
					}

					if (args[0].equalsIgnoreCase("tp")) {
						if (args.length == 2) {
							int u = 0;
							if(args[1].equalsIgnoreCase("start")){
								if(getConfig().contains("Checkpoint.start")){
								player.teleport(getStartLoc());
								player.sendMessage(starter("§aTu viens d'être téléporté au start!"));
								} else {
									player.sendMessage(starter("§cTu n'as pas set le start !"));
								}
								return true;
							}
							if(args[1].equalsIgnoreCase("finish")){
								if(getConfig().contains("Checkpoint.finish")){
								player.teleport(getFinishLoc());
								player.sendMessage(starter("§aTu viens d'être téléporté au finish!"));
							} else {
								player.sendMessage(starter("§cTu n'as pas set le finish !"));
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
							if(getConfig().contains("Checkpoint."+u)){
							player.teleport(getCheckPointLocById(u, player));
							} else {
								player.sendMessage(starter("§cL'id n'est pas enregistrée!"));
							}
						}
						if (args.length == 1) {
							player.sendMessage(starter("§cIl manque des args:"));
							player.sendMessage(starter("§c/jump tp <id>"));
							player.sendMessage(starter("§c/jump tp start"));
							player.sendMessage(starter("§c/jump tp <finish>"));
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
			player.sendMessage(starter("§aTu viens d'être téléporté au checkpoint <" + id + "> !"));
			return new Location(w, x, y, z, yaw, pitch);
		}
		return player.getLocation();	
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
			float yaw, String world, String name, boolean isName, boolean isId, int id) {
		reloadConfig();
			
			//SI isId ALORS SET CHECKPOINT + ID + X;
		if (!isName) {
			if(!isId){
			int i = getNextAndSetInt();
			getConfig().set("Checkpoint." + i + ".x", x);
			getConfig().set("Checkpoint." + i + ".y", y);
			getConfig().set("Checkpoint." + i + ".z", z);
			getConfig().set("Checkpoint." + i + ".pitch", pitch);
			getConfig().set("Checkpoint." + i + ".yaw", yaw);
			getConfig().set("Checkpoint." + i + ".world", world);
			} else {
				if(getConfig().contains("Checkpoint." + id)){
				getConfig().set("Checkpoint." + id + ".x", x);
				getConfig().set("Checkpoint." + id + ".y", y);
				getConfig().set("Checkpoint." + id + ".z", z);
				getConfig().set("Checkpoint." + id + ".pitch", pitch);
				getConfig().set("Checkpoint." + id + ".yaw", yaw);
				getConfig().set("Checkpoint." + id + ".world", world);
				}
			}
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
		i = Checkpoints + 1;
		getConfig().set("Checkpoints", i);
		saveConfig();
		return i;
	}

	@SuppressWarnings("unused")
	private void removeCheckpoint(int id) {
		int i = 0;
		i = Checkpoints;
		i--;
		getConfig().set("Checkpoints", i);

		saveConfig();
	}

	@SuppressWarnings("unused")
	private boolean checkpointExists(int id) {
		if (getConfig().contains("Checkpoint." + id))
			return true;
		return false;
	}

	public String starter(String msg) {
		return "§7[" + "§cJump" + "§7] §6" + msg;
	}
}

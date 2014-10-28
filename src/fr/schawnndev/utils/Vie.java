package fr.schawnndev.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Vie {

	Player plaayer = null;
	int vie = 0;
	public HashMap<Player, Integer> playerVies = new HashMap<Player, Integer>();

	public Vie(Player player, int vies) {
		this.plaayer = player;
		this.vie = vies;
	}

	public void add(int nombreVie) {
		this.playerVies.put(plaayer, this.vie);
	}

	public void remove(int nombreVie) {
		this.playerVies.remove(plaayer);
	}

	public int get() {
		return this.playerVies.get(plaayer);
	}

	public boolean containsPlayer() {
		if (this.playerVies.containsKey(this.plaayer))
			return true;
		return false;
	}

}

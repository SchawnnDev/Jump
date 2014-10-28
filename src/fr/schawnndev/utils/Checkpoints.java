package fr.schawnndev.utils;

import org.bukkit.entity.Player;

public class Checkpoints {

	Player players = null;
	int checkpoints = 0;

	public Checkpoints(Player player) {
		this.players = player;
	}

	public int get() {
		return this.checkpoints;
	}
	
	public int set(int checkpoint){
		return this.checkpoints;
	}

}

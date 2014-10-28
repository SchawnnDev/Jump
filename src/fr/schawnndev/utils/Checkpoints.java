package fr.schawnndev.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.schawnndev.Main;

public class Checkpoints {

	Player players = null;
	int checkpoints = 0;
	public HashMap<Player, Integer> playerCheckPoint = new HashMap<Player, Integer>();

	public Checkpoints(Player player) {
		this.players = player;
	}

	public int getCheckpoint() {
		return this.playerCheckPoint.get(players);
	}

	public int setCheckpoint(int checkpoint) {
		return this.playerCheckPoint.put(players, checkpoint);
	}
	
	public void addCheckpoint(){
		if(this.playerCheckPoint.get(players) < Main.getInstance().getConfig().getInt("LastCheckpoint")){
			
		} else {
			int a = this.playerCheckPoint.get(players) + 1;
			this.playerCheckPoint.put(players, a);
		}
	}

	public boolean containsPlayer() {
		if (this.playerCheckPoint.containsKey(players))
			return true;
		return false;
	}

}

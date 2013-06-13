package groz.entity;

import groz.DualStats;
import groz.Stats;
import groz.entity.attack.Attack;

/**
 * This is the class for the Player
 * 
 * @author Texasjake95
 */
public class Player extends Entity {

	public Player(String playername, int lvl) {
		super(playername, new DualStats(new Stats(0, 10, 2, 2, 2, 2, lvl)));
		this.getStats().setHealth(10);
	}

	public void setLevel(int lvl) {
		this.getStats().setLvl(lvl);
	}

	@Override
	public Player addAttack(Attack attack) {
		// TODO Auto-generated method stub
		return (Player) super.addAttack(attack);
	}
}

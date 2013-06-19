package groz.entity;

import groz.Stats;
import groz.entity.attack.Attack;

/**
 * This is the class for the Player
 * 
 * @author Texasjake95
 */
public class Player extends Entity {
	
	private int lvl;
	
	public Player(String playername, int lvl)
	{
		super(playername, new Stats(0, 10, 4, 4, 4, 4));
	}
	
	public void setLevel(int lvl)
	{
		this.lvl = lvl;
	}
	
	public int getLevel()
	{
		return this.lvl;
	}
	
	@Override
	public Player addAttack(Attack attack, int index)
	{
		return (Player) super.addAttack(attack, index);
	}
}

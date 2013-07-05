package groz.entity;

import groz.Groz;
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
		super(playername, new PlayerStats(0, 10, 4, 4, 4, 4));
		if (!Groz.saveFile.doesSaveFileHave("String.playerName"))
		{
			Groz.saveFile.setString("playerName", this.getName());
		}
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
	public PlayerStats getStats()
	{
		return (PlayerStats) super.getStats();
	}
	
	@Override
	public Player addAttack(Attack attack, int index)
	{
		return (Player) super.addAttack(attack, index);
	}
}

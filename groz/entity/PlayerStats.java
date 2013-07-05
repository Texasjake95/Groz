package groz.entity;

import groz.Stats;

public class PlayerStats extends Stats {
	
	public PlayerStats(int HP, int BaseHealth, int ATK, int DEF, int SPD, int AGL)
	{
		super(HP, BaseHealth, ATK, DEF, SPD, AGL);
	}
	
	public void setAgility(int value)
	{
		this.AGL = value;
	}
	
	public void setAttack(int value)
	{
		this.ATK = value;
	}
	
	public void setBaseHealth(int value)
	{
		this.BaseHealth = value;
	}
	
	public void setDefense(int value)
	{
		this.DEF = value;
	}
	
	public void setHealth(int value)
	{
		this.Health = value;
	}
	
	public void setHP(int value)
	{
		this.HP = value;
		this.setMaxHealth(this.getBaseHealth() + this.getHP() * 5);
	}
	
	public void setMaxHealth(int value)
	{
		this.MaxHealth = value;
	}
	
	public void setSpeed(int value)
	{
		this.SPD = value;
	}
}

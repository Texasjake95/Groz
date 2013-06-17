package groz;

import groz.entity.Entity;

/**
 * This class is used to calculated the stats of any {@link Entity}, there are
 * normal two per {@link Entity} one for Base Stats and one for the Level Up
 * stats.
 * 
 * @author Texasjake95
 */
public class Stats {
	
	protected static int MAXLEVEL = 100;
	private int MaxHealth;
	private int Health;
	private int HP;
	private int ATK;
	private int DEF;
	private int SPD;
	private int AGL;
	private int BaseHealth;
	
	public Stats(int HP, int BaseHealth, int ATK, int DEF, int SPD, int AGL)
	{
		this.HP = HP;
		this.BaseHealth = BaseHealth;
		this.ATK = ATK;
		this.DEF = DEF;
		this.SPD = SPD;
		this.AGL = AGL;
	}
	
	public int getAgility()
	{
		return this.AGL;
	}
	
	public int getAttack()
	{
		return this.ATK;
	}
	
	public int getBaseHealth()
	{
		return BaseHealth;
	}
	
	public int getDefense()
	{
		return this.DEF;
	}
	
	public int getHealth()
	{
		return Health;
	}
	
	public int getHP()
	{
		return HP;
	}
	
	public int getMaxHealth()
	{
		MaxHealth = (this.getHP() * 5 + this.BaseHealth);
		return MaxHealth;
	}
	
	public int getSpeed()
	{
		return this.SPD;
	}
	
	public void addHP(int add)
	{
		if ((this.getHP() + add) >= MAXLEVEL)
		{
			this.HP = MAXLEVEL;
		}
		else
		{
			this.HP += add;
		}
		this.MaxHealth = (this.getHP() * 5) + this.BaseHealth;
	}
}
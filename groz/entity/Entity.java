package groz.entity;

import groz.Stats;
import groz.entity.attack.Attack;

/**
 * This is the base Entity
 * 
 * @author Texasjake95
 */
public class Entity {
	
	private String Name;
	private Stats stats;
	private Attack attacks[] = new Attack[4];
	
	public Entity(String name, Stats stats)
	{
		this.Name = name;
		this.stats = stats;
	}
	
	public Entity addAttack(Attack attack)
	{
		for (int x = 0; x < 4; x++)
		{
			if (this.attacks[x] == null)
			{
				this.attacks[x] = attack;
				return this;
			}
		}
		return this;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public Stats getStats()
	{
		return this.stats;
	}
	
	public Attack getAttack(int index)
	{
		/*
		 * if (this.attacks[index] == null) { for (int x = index; x >= 0; x--) {
		 * if (this.attacks != null) { return this.attacks[x]; } } for (int x =
		 * 0; x < 4; x++) { if (this.attacks != null) { return this.attacks[x];
		 * } } }
		 */
		return new Attack(.5d, .9d).setGivesDamage(true);
	}
}

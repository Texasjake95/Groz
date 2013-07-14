package com.texasjake95.groz.entity;

import com.texasjake95.groz.Stats;
import com.texasjake95.groz.entity.attack.Attack;

/**
 * This is the base Entity
 * 
 * @author Texasjake95
 */
public class GrozEntity {
	
	private String Name;
	private Stats stats;
	private Attack attacks[] = new Attack[4];
	
	public GrozEntity(String name, Stats stats)
	{
		this.Name = name;
		this.stats = stats;
	}
	
	public GrozEntity addAttack(Attack attack, int index)
	{
		if (this.attacks[index] == null)
		{
			this.attacks[index] = attack;
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
		if (this.attacks[index] == null)
		{
			for (int x = index; x >= 0; x--)
			{
				if (this.attacks[x] != null)
				{
					return this.attacks[x];
				}
			}
			for (int x = 0; x < 4; x++)
			{
				if (this.attacks[x] != null)
				{
					return this.attacks[x];
				}
			}
			return new Attack(.5d, .9d).setGivesDamage(true);
		}
		return this.attacks[index];
	}
	
	public Attack[] getAttacks()
	{
		return this.attacks;
	}
}

package groz.entity.attack.effect;

import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.attack.effect.StatChanger.EnumModifier;
import groz.entity.attack.effect.StatChanger.StatModifier;

/**
 * @author Texasjake95
 */
public class Effect implements Comparable<Effect> {
	
	private double attackChance;
	private StatChanger change;
	private int id;
	public static final Effect Paralyze = new Effect(.50d, 0, new StatChanger().addStats(EnumStatType.SPD, EnumModifier.Multiply, .5d));
	public static final Effect Sleep = new Effect(0.0, 1, new StatChanger());
	public static final Effect Posion = new Effect(1.0d, 2, new StatChanger().addStats(EnumStatType.Health, EnumModifier.Subtract, 3));
	
	Effect(double Chan, int id, StatChanger Change)
	{
		this.attackChance = Chan;
		this.id = id;
		this.change = Change;
	}
	
	public StatChanger getChange()
	{
		return this.change;
	}
	
	public double changeStat(EnumStatType type, double value)
	{
		if (this.change.stat.containsKey(type))
		{
			StatModifier mod = this.change.stat.get(type);
			switch (mod.getModifier())
			{
				case Add:
					return add(mod.getValue(), value);
				case Divide:
					return divide(mod.getValue(), value);
				case Multiply:
					return multiply(mod.getValue(), value);
				case Subtract:
					return subtract(mod.getValue(), value);
				default:
					return value;
			}
		}
		return value;
	}
	
	private double add(double d, double value)
	{
		return value + d;
	}
	
	private double subtract(double d, double value)
	{
		return value - d;
	}
	
	private double multiply(double d, double value)
	{
		return value * d;
	}
	
	private double divide(double d, double value)
	{
		return value / d;
	}
	
	public boolean canAttack(double d)
	{
		System.out.println("Effect Chance: " + d);
		System.out.println("Attack Chance: " + this.attackChance);
		return d < this.attackChance;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Effect arg0)
	{
		return this.id - arg0.id;
	}
}

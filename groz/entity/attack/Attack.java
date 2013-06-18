package groz.entity.attack;

import groz.Groz;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.InGameEntity;
import groz.entity.Monster;
import groz.entity.Player;
import groz.entity.attack.effect.Effect;
import groz.util.logging.GrozLogger;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * This is the class that allows the {@link Player} attack a {@link Monster} and
 * visa-versa
 * 
 * @author Texasjake95
 */
public class Attack {
	
	public String Name;
	private Random rand = new Random();
	private Random crt = new Random();
	private final double damagePer;
	private final double accuracy;
	private boolean givesDam = true;
	private double crtHitRatio = .1d;
	private final ArrayList<Effect> effects = new ArrayList<Effect>();
	private Map<Effect, Double> effectChan = new TreeMap<Effect, Double>();
	
	// public static Attack[] batAttacks = new Attack[]{new Attack(.75, .9), new
	// Attack(.5, .9).setEffects(effects, ds)};
	public Attack(double damage, double accuracy)
	{
		this(damage, accuracy, "");
	}
	
	public Attack(double damage, double accuracy, String name)
	{
		this.damagePer = damage;
		this.accuracy = accuracy;
		this.Name = name;
	}
	
	public boolean givesDamage()
	{
		return this.givesDam;
	}
	
	public Attack setGivesDamage(boolean givesDam)
	{
		this.givesDam = givesDam;
		return this;
	}
	
	public double getDamagePer()
	{
		return this.damagePer;
	}
	
	public Attack setEffects(ArrayList<Effect> effects, double[] ds)
	{
		if (ds.length == effects.size())
		{
			this.effects.addAll(effects);
			for (int i = 0; i < effects.size(); i++)
			{
				this.effectChan.put(effects.get(i), ds[i]);
			}
		}
		else
		{
		}
		return this;
	}
	
	public Attack addEffects(Effect effect, double ds)
	{
		this.effects.add(effect);
		this.effectChan.put(effect, ds);
		return this;
	}
	
	public boolean hasEffects()
	{
		return this.effects != null;
	}
	
	public void doAttack(InGameEntity attacker, InGameEntity victim)
	{
		GrozLogger.logGame("Attacker: " + attacker.getEntity().getName());
		GrozLogger.logGame(attacker.getEntity().getName() + ": " + attacker.getHealth() + " HP");
		GrozLogger.logGame(victim.getEntity().getName() + ": " + victim.getHealth() + " HP");
		if (Groz.DEBUG)
		{
			GrozLogger.logGame(attacker.getEntity().getName() + " MAX: " + attacker.getMaxHealth());
			GrozLogger.logGame(victim.getEntity().getName() + " MAX: " + victim.getMaxHealth());
		}
		double aglilitya = attacker.preformEffect(EnumStatType.AGL, attacker.getAgility() - nextInt(attacker.getAgility() / 2));
		if (Groz.DEBUG)
			GrozLogger.logGame(aglilitya);
		double aglilityv = victim.preformEffect(EnumStatType.AGL, victim.getAgility() - nextInt(victim.getAgility() / 2));
		if (Groz.DEBUG)
			GrozLogger.logGame(aglilityv);
		double hitChan = (aglilitya / aglilityv);
		if (hitChan < 0)
		{
			hitChan *= -1;
		}
		while (hitChan > 1)
		{
			hitChan -= 1d;
		}
		hitChan *= this.accuracy;
		if (Groz.DEBUG)
			GrozLogger.logGame("Hit Chance: " + hitChan);
		double hitChan2 = rand.nextDouble();
		if (Groz.DEBUG)
			GrozLogger.logGame("Hit Chance 2: " + hitChan2);
		if (hitChan2 >= hitChan)
		{
			GrozLogger.logGame("Attack Missed!");
		}
		else
		{
			if (this.givesDamage())
			{
				if (!victim.isDead())
				{
					victim.damage(attacker, this, rand, isCrt());
					this.addEffect(this, victim);
				}
				if (victim.isDead())
				{
					GrozLogger.logGame(victim.getEntity().getName() + " is dead");
					victim = null;
				}
			}
			else
			{
				if (!victim.isDead())
				{
					attacker.heal(this, rand);
					this.addEffect(this, attacker);
				}
				if (victim.isDead())
				{
					GrozLogger.logGame(attacker.getEntity().getName() + " is dead");
				}
			}
		}
		GrozLogger.logGame("");
	}
	
	private void addEffect(Attack attack, InGameEntity victim)
	{
		for (Effect effect : attack.effects)
		{
			double chan = this.effectChan.get(effect);
			if (rand.nextDouble() < chan)
			{
				victim.addEffect(effect, 5);
			}
		}
	}
	
	public boolean isCrt()
	{
		return crt.nextDouble() < this.crtHitRatio;
	}
	
	public int nextInt(double d)
	{
		int b = (int) d;
		if (b <= 0)
		{
			b = 1;
		}
		if (b < 0)
		{
			b *= -1;
		}
		return rand.nextInt(b);
	}
	
	public Attack setName(String name)
	{
		this.Name = name;
		return this;
	}
	
	public String getName()
	{
		return this.Name;
	}
}

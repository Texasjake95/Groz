package com.texasjake95.groz.entity;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.texasjake95.groz.Groz;
import com.texasjake95.groz.entity.EnumMonsterType.EnumStatType;
import com.texasjake95.groz.entity.attack.Attack;
import com.texasjake95.groz.entity.attack.effect.Effect;
import com.texasjake95.groz.util.logging.GrozLogger;

public class InGameEntity {
	
	protected static final Random rand = new Random();
	private Map<Effect, Integer> effects = new TreeMap<Effect, Integer>();
	private GrozEntity entity;
	public int lvl;
	protected int Health;
	protected int HP;
	protected int ATK;
	protected int DEF;
	protected int SPD;
	protected int AGL;
	
	public InGameEntity(GrozEntity entity, int lvl)
	{
		this.entity = entity;
		this.lvl = lvl;
		if (this.lvl <= 0)
			this.lvl = 1;
		this.Health = entity.getStats().getMaxHealth();
		this.HP = entity.getStats().getHP();
		this.ATK = entity.getStats().getAttack();
		this.DEF = entity.getStats().getDefense();
		this.SPD = entity.getStats().getSpeed();
		this.AGL = entity.getStats().getAgility();
	}
	
	public int getLevel()
	{
		return this.lvl;
	}
	
	public int getAgility()
	{
		return this.AGL;
	}
	
	public int getAttack()
	{
		return this.ATK;
	}
	
	public int getDefense()
	{
		return this.DEF;
	}
	
	public int getHealth()
	{
		return this.Health;
	}
	
	public int getHP()
	{
		return this.HP;
	}
	
	public int getMaxHealth()
	{
		return this.getHP() * 5 + this.entity.getStats().getBaseHealth();
	}
	
	public int getSpeed()
	{
		return this.SPD;
	}
	
	public GrozEntity getEntity()
	{
		return this.entity;
	}
	
	public double preformEffect(EnumStatType type, double value)
	{
		double temp = value;
		for (Effect effect : effects.keySet())
		{
			temp = effect.changeStat(type, temp);
		}
		return temp;
	}
	
	public void damage(InGameEntity attacker, Attack attack, Random rand, boolean isCrt)
	{
		int EAttack = (int) attacker.preformEffect(EnumStatType.ATK, attacker.getAttack() - rand.nextInt(attacker.getAttack() / 2));
		int Defense = (int) this.preformEffect(EnumStatType.DEF, this.DEF - rand.nextInt(this.DEF / 2));
		EAttack *= attack.getDamagePer();
		if (Groz.DEBUG)
			GrozLogger.logGame("Base Attack: " + EAttack, 0);
		if (isCrt)
		{
			GrozLogger.logGame("Critical Hit!", 0);
			EAttack *= 1.50;
		}
		int damage = EAttack - Defense;
		if (damage <= 0)
		{
			damage = 1;
		}
		if (Groz.DEBUG)
		{
			GrozLogger.logGame("Attack: " + EAttack, 0);
			GrozLogger.logGame("Defense: " + Defense, 0);
		}
		GrozLogger.logGame("Damage: " + damage, .5);
		this.subtractHealth(damage);
	}
	
	public void subtractHealth(int damage)
	{
		this.Health -= damage;
		if (this.Health < 0)
			this.Health = 0;
	}
	
	public void heal(Attack attack, Random rand)
	{
		int heal = (int) this.preformEffect(EnumStatType.ATK, rand.nextInt(this.ATK / 2));
		heal *= attack.getDamagePer();
		if (heal <= 0)
		{
			heal = 1;
		}
		GrozLogger.logGame("Heal:" + heal, .5);
		if (this.Health + heal < this.getMaxHealth())
			this.Health += heal;
		else
			this.Health = this.getMaxHealth();
	}
	
	public boolean isDead()
	{
		return this.Health <= 0;
	}
	
	public void addEffect(Effect effect, int i)
	{
		this.effects.put(effect, i);
		GrozLogger.logGame("Is Paralyzed!", 1.5);
	}
	
	public boolean hadAnyEffects()
	{
		return effects.size() > 0;
	}
	
	public boolean hasEffect(Effect effect)
	{
		return effects.containsKey(effect);
	}
	
	public boolean canAttack()
	{
		for (Effect effect : this.effects.keySet())
		{
			int temp = this.effects.get(effect);
			this.effects.remove(effect);
			if (temp - 1 > 0)
			{
				this.effects.put(effect, temp - 1);
			}
		}
		for (Effect effect : this.effects.keySet())
		{
			double temp = rand.nextDouble();
			if (!effect.canAttack(temp))
			{
				return false;
			}
		}
		return true;
	}
	
	public void setHealth(int health)
	{
		this.Health = health;
	}
	
	public void setDead()
	{
		this.Health = 0;
	}
}

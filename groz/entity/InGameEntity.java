package groz.entity;

import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.attack.Attack;
import groz.entity.attack.effect.Effect;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class InGameEntity {
	
	protected static final Random rand = new Random();
	private Map<Effect, Integer> effects = new TreeMap<Effect, Integer>();
	private Entity entity;
	public int lvl;
	protected int MaxHealth;
	protected int Health;
	protected int HP;
	protected int ATK;
	protected int DEF;
	protected int SPD;
	protected int AGL;
	
	public InGameEntity(Entity entity, int lvl)
	{
		this.entity = entity;
		this.lvl = lvl;
		this.MaxHealth = entity.getStats().getMaxHealth();
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
		return this.MaxHealth;
	}
	
	public int getSpeed()
	{
		return this.SPD;
	}
	
	public Entity getEntity()
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
		System.out.println("Base Attack: " + EAttack);
		if (isCrt)
		{
			System.out.println("Critical Hit!");
			EAttack *= 1.50;
		}
		int damage = EAttack - Defense;
		if (damage <= 0)
		{
			damage = 1;
		}
		System.out.println("Attack: " + EAttack);
		System.out.println("Defense: " + Defense);
		System.out.println("Damage: " + damage);
		this.subtractHealth(damage);
	}
	
	public void subtractHealth(int damage)
	{
		this.Health -= damage;
	}
	
	public void heal(Attack attack, Random rand)
	{
		int heal = (int) this.preformEffect(EnumStatType.ATK, rand.nextInt(this.ATK / 2));
		heal *= attack.getDamagePer();
		if (heal <= 0)
		{
			heal = 1;
		}
		if (this.Health + heal < this.MaxHealth)
			this.Health += heal;
		else
			this.Health = this.MaxHealth;
	}
	
	public boolean isDead()
	{
		return this.Health <= 0;
	}
	
	public void addEffect(Effect effect, int i)
	{
		this.effects.put(effect, i);
		System.out.println("Is Paralyzed!");
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
}

package groz.entity;

import groz.DualStats;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.attack.Attack;
import groz.entity.attack.effect.Effect;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * This is the base Entity
 * 
 * @author Texasjake95
 */
public class Entity {

	private Random rand = new Random();
	private String Name;
	private DualStats stats;
	private int health = 0;
	private Map<Effect, Integer> effects = new TreeMap<Effect, Integer>();
	private Attack attacks[] = new Attack[4];

	public Entity(String name, DualStats stats) {
		this.Name = name;
		this.stats = stats;
		this.health = this.stats.getMaxHealth();
	}

	public double preformEffect(EnumStatType type, double value) {
		double temp = value;
		for (Effect effect : effects.keySet()) {
			temp = effect.changeStat(type, temp);
		}
		return temp;
	}

	public void damage(Entity attacker, Attack attack, Random rand,
			boolean isCrt) {
		int EAttack = (int) attacker.preformEffect(
				EnumStatType.ATK,
				attacker.stats.getAttack()
						- rand.nextInt(attacker.stats.getAttack() / 2));
		int Defense = (int) this.preformEffect(
				EnumStatType.DEF,
				this.stats.getDefense()
						- rand.nextInt(this.stats.getDefense() / 2));
		EAttack *= attack.getDamagePer();
		System.out.println("Base Attack: " + EAttack);
		if (isCrt) {
			System.out.println("Critical Hit!");
			EAttack *= 1.50;
		}
		int damage = EAttack - Defense;
		if (damage <= 0) {
			damage = 1;
		}
		System.out.println("Attack: " + EAttack);
		System.out.println("Defense: " + Defense);
		System.out.println("Damage: " + damage);
		this.subtractHealth(damage);
	}

	/**
	 * @param damage
	 */
	private void subtractHealth(int damage) {
		this.health -= damage;
	}

	public void heal(Entity attacker, Attack attack, Random rand) {
		int heal = (int) attacker.preformEffect(EnumStatType.ATK,
				rand.nextInt(attacker.stats.getAttack() / 2));
		heal *= attack.getDamagePer();
		if (heal <= 0) {
			heal = 1;
		}
		this.health += heal;
	}

	public String getName() {
		return this.Name;
	}

	public DualStats getStats() {
		return this.stats;
	}

	public boolean isDead() {
		return this.health <= 0;
	}

	public void addEffect(Effect effect, int i) {
		this.effects.put(effect, i);
		System.out.println("Is Paralyzed!");
	}

	public boolean hadAnyEffects() {
		return effects.size() > 0;
	}

	public boolean hasEffect(Effect effect) {
		return effects.containsKey(effect);
	}

	public boolean canAttack() {
		for (Effect effect : this.effects.keySet()) {
			int temp = this.effects.get(effect);
			this.effects.remove(effect);
			if (temp - 1 > 0) {
				this.effects.put(effect, temp - 1);
			}
		}
		for (Effect effect : this.effects.keySet()) {
			double temp = rand.nextDouble();
			if (!effect.canAttack(temp)) {
				return false;
			}
		}
		return true;
	}

	public Entity addAttack(Attack attack) {
		for (int x = 0; x < 4; x++) {
			if (this.attacks[x] == null) {
				this.attacks[x] = attack;
				return this;
			}
		}
		return this;
	}

	public Attack getAttack(int index) {
		/*
		 * if (this.attacks[index] == null) { for (int x = index; x >= 0; x--) {
		 * if (this.attacks != null) { return this.attacks[x]; } } for (int x =
		 * 0; x < 4; x++) { if (this.attacks != null) { return this.attacks[x];
		 * } } }
		 */
		return new Attack(.5d, .9d).setGivesDamage(false);
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return this.health;
	}
}

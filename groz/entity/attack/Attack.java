package groz.entity.attack;

import groz.entity.Entity;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.Monster;
import groz.entity.Player;
import groz.entity.attack.effect.Effect;

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

	private Random rand = new Random();
	private Random crt = new Random();
	private double damagePer;
	private double accuracy;
	private boolean givesDam = true;
	private double crtHitRatio = .1d;
	private final ArrayList<Effect> effects = new ArrayList<Effect>();
	private Map<Effect, Double> effectChan = new TreeMap<Effect, Double>();

	// public static Attack[] batAttacks = new Attack[]{new Attack(.75, .9), new
	// Attack(.5, .9).setEffects(effects, ds)};
	public Attack(double damage, double accuracy) {
		this.damagePer = damage;
		this.accuracy = accuracy;
	}

	public boolean givesDamage() {
		return this.givesDam;
	}

	public Attack setGivesDamage(boolean givesDam) {
		this.givesDam = givesDam;
		return this;
	}

	public double getDamagePer() {
		return this.damagePer;
	}

	public Attack setEffects(ArrayList<Effect> effects, double[] ds) {
		if (ds.length == effects.size()) {
			this.effects.addAll(effects);
			for (int i = 0; i < effects.size(); i++) {
				this.effectChan.put(effects.get(i), ds[i]);
			}
		} else {
		}
		return this;
	}

	public Attack addEffects(Effect effect, double ds) {
		this.effects.add(effect);
		this.effectChan.put(effect, ds);
		return this;
	}

	public boolean hasEffects() {
		return this.effects != null;
	}

	public void doAttack(Entity attacker, Entity victim) {
		System.out.println("");
		System.out.println("Attacker: " + attacker.getName());
		System.out.println(attacker.getName() + ": "
				+ attacker.getStats().getHealth());
		System.out.println(victim.getName() + ": "
				+ victim.getStats().getHealth());
		System.out.println(attacker.getName() + " MAX: "
				+ attacker.getStats().getMaxHealth());
		System.out.println(victim.getName() + " MAX: "
				+ victim.getStats().getMaxHealth());
		double aglilitya = attacker.preformEffect(EnumStatType.AGL, attacker
				.getStats().getAgility()
				- nextInt(attacker.getStats().getAgility() / 2));
		System.out.println(aglilitya);
		double aglilityv = victim.preformEffect(EnumStatType.AGL, victim
				.getStats().getAgility()
				- nextInt(victim.getStats().getAgility() / 2));
		System.out.println(aglilityv);
		double hitChan = (aglilitya / aglilityv);
		if (hitChan < 0) {
			hitChan *= -1;
		}
		while (hitChan > 1) {
			hitChan -= 1d;
		}
		hitChan *= this.accuracy;
		System.out.println("Hit Chance: " + hitChan);
		double hitChan2 = rand.nextDouble();
		System.out.println("Hit Chance 2: " + hitChan2);
		if (hitChan2 >= hitChan) {
			System.out.println("Attack Missed!");
		} else {
			if (this.givesDamage()) {
				if (!victim.isDead()) {
					victim.damage(attacker, this, rand, isCrt());
					this.addEffect(this, victim);
				}
				if (victim.isDead()) {
					System.out.println(victim.getName() + " is dead");
					victim = null;
				}
			} else {
				if (!victim.isDead()) {

					attacker.heal(attacker, this, rand);
					this.addEffect(this, attacker);
				}
				if (victim.isDead()) {
					System.out.println(attacker.getName() + " is dead");
				}
			}
		}
	}

	private void addEffect(Attack attack, Entity victim) {
		for (Effect effect : attack.effects) {
			double chan = this.effectChan.get(effect);
			if (rand.nextDouble() < chan) {
				victim.addEffect(effect, 5);
			}
		}
	}

	public boolean isCrt() {
		return crt.nextDouble() < this.crtHitRatio;
	}

	public int nextInt(double d) {
		int b = (int) d;
		if (b <= 0) {
			b = 1;
		}
		if (b < 0) {
			b *= -1;
		}
		return rand.nextInt(b);
	}
}

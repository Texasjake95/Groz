package groz;

import groz.entity.Entity;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.Monster;
import groz.entity.Player;
import groz.zone.ZoneBase;

import java.util.Random;

/**
 * This is the class that Groz uses to commence a battle
 * 
 * @author Texasjake95
 */
public class Battle {
	
	/**
	 * The attacking {@link Monster}
	 */
	public Entity monster;
	/**
	 * The {@link Player} defending
	 */
	public Player player;
	private Random rand;
	private Random crt;
	
	/**
	 * 
	 * @param player
	 *            The Player
	 * @param zone
	 *            The Zone the {@link Battle} will be fought in
	 */
	public Battle(Player player, ZoneBase zone)
	{
		this.player = player;
		crt = new Random();
		rand = new Random();
		System.out.println("Zone " + zone.xCord() + ", " + zone.yCord());
		monster = Monster.getDynLvl(player, zone);
		System.out.println(monster.getName());
		System.out.println("LVL: " + monster.getStats().getLvl());
		System.out.println("HP: " + monster.getStats().getHP());
		int attackc = monster.getStats().getAttack() - monster.getStats().getBaseStats().getAttack();
		System.out.println("ATK: " + monster.getStats().getAttack() + " + " + attackc);
		System.out.println("DEF: " + monster.getStats().getDefense());
		System.out.println("SPD: " + monster.getStats().getSpeed());
		System.out.println("AGL: " + monster.getStats().getAgility());
	}
	
	/**
	 * Does attack to victim using the Attacker's {@link Attack} list
	 * 
	 * @param attacker
	 *            The attacker
	 * @param attackIndex
	 * @param victim
	 *            The Victim
	 */
	public void Attack(Entity attacker, int attackIndex, Entity victim)
	{
		if (attacker.canAttack())
		{
			attacker.getAttack(attackIndex).doAttack(attacker, victim);
		}
		else
		{
			System.out.println("");
			System.out.println("Attacker: " + attacker.getName());
			System.out.println(attacker.getName() + ": " + attacker.getHealth());
			System.out.println(victim.getName() + ": " + victim.getHealth());
			System.out.println("Paralyzed!");
		}
		attacker.getStats().setHealth((int) attacker.preformEffect(EnumStatType.Health, attacker.getStats().getHealth()));
	}
	
	/**
	 * 
	 * @return Can this {@link Battle} continue
	 */
	public boolean canContinue()
	{
		return !monster.isDead() && !player.isDead();
	}
	
	public boolean isCrt()
	{
		return crt.nextDouble() < .1d;
	}
	
	public int nextInt(double d)
	{
		int b = (int) d;
		if (b < 0)
		{
			b *= -1;
		}
		return rand.nextInt(b);
	}
}

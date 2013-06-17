package groz;

import groz.entity.DynMonster;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.InGameEntity;
import groz.entity.InGamePlayer;
import groz.entity.Monster;
import groz.entity.Player;
import groz.entity.attack.Attack;
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
	public DynMonster monster;
	/**
	 * The {@link Player} defending
	 */
	public InGamePlayer player;
	private Random rand;
	private Random crt;
	
	/**
	 * 
	 * @param player
	 *            The Player
	 * @param zone
	 *            The Zone the {@link Battle} will be fought in
	 */
	public Battle(InGamePlayer player, ZoneBase zone)
	{
		crt = new Random();
		rand = new Random();
		System.out.println("Zone " + zone.xCord() + ", " + zone.yCord());
		monster = DynMonster.getDynLvl(player, zone);
		System.out.println(monster.getEntity().getName());
		System.out.println("LVL: " + monster.getLevel());
		System.out.println("HP: " + monster.getHP());
		System.out.println("Health: " + monster.getHealth());
		System.out.println("ATK: " + monster.getAttack());
		System.out.println("DEF: " + monster.getDefense());
		System.out.println("SPD: " + monster.getSpeed());
		System.out.println("AGL: " + monster.getAgility());
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
	public void Attack(InGameEntity attacker, int attackIndex, InGameEntity victim)
	{
		if (attacker.canAttack())
		{
			attacker.getEntity().getAttack(attackIndex).doAttack(attacker, victim);
		}
		else
		{
			System.out.println("");
			System.out.println("Attacker: " + attacker.getEntity().getName());
			System.out.println(attacker.getEntity().getName() + ": " + attacker.getHealth());
			System.out.println(victim.getEntity().getName() + ": " + victim.getHealth());
			System.out.println("Paralyzed!");
		}
		attacker.setHealth((int) attacker.preformEffect(EnumStatType.Health, attacker.getHealth()));
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

package groz;

import groz.entity.DynMonster;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.InGameEntity;
import groz.entity.InGamePlayer;
import groz.entity.Monster;
import groz.entity.Player;
import groz.entity.attack.Attack;
import groz.util.logging.GrozLogger;
import groz.zone.ZoneBase;

import java.util.Random;
import java.util.logging.Level;

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
		this.player = player;
		crt = new Random();
		rand = new Random();
		GrozLogger.logGame("Zone " + zone.xCord() + ", " + zone.yCord());
		monster = DynMonster.getDynLvl(player, zone);
		GrozLogger.logGame(monster.getEntity().getName() + " has appeared!");
		if (Groz.DEBUG)
		{
			GrozLogger.logGame("LVL: " + monster.getLevel());
			GrozLogger.logGame("HP: " + monster.getHP());
		}
		GrozLogger.logGame("Health: " + monster.getHealth());
		if (Groz.DEBUG)
		{
			GrozLogger.logGame("ATK: " + monster.getAttack());
			GrozLogger.logGame("DEF: " + monster.getDefense());
			GrozLogger.logGame("SPD: " + monster.getSpeed());
			GrozLogger.logGame("AGL: " + monster.getAgility());
			GrozLogger.logGame(Level.INFO, "Is Player null:" + (this.player == null));
		}
		GrozLogger.logGame("");
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
			GrozLogger.logGame("");
			GrozLogger.logGame("Attacker: " + attacker.getEntity().getName());
			GrozLogger.logGame(attacker.getEntity().getName() + ": " + attacker.getHealth());
			GrozLogger.logGame(victim.getEntity().getName() + ": " + victim.getHealth());
			GrozLogger.logGame(attacker.getEntity().getName() + " is paralyzed!");
		}
		attacker.setHealth((int) attacker.preformEffect(EnumStatType.Health, attacker.getHealth()));
	}
	
	/**
	 * 
	 * @return Can this {@link Battle} continue
	 */
	public boolean canContinue()
	{
		return monster == null || player == null ? false : !monster.isDead() && !player.isDead();
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

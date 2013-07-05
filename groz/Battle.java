package groz;

import groz.entity.DynMonster;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.InGameEntity;
import groz.entity.InGamePlayer;
import groz.entity.Monster;
import groz.entity.Player;
import groz.entity.attack.Attack;
import groz.util.InteractiveHelper;
import groz.util.logging.GrozLogger;
import groz.zone.ZoneBase;

import java.util.Random;
import java.util.logging.Level;

/**
 * This is the class that Groz uses to commence a this
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
		GrozLogger.logGame("Zone " + zone.xCord() + ", " + zone.yCord(), .5);
		monster = DynMonster.getDynLvl(player, zone);
		GrozLogger.logGame(monster.getEntity().getName() + " has appeared!", .5);
		if (Groz.DEBUG)
		{
			GrozLogger.logGame("LVL: " + monster.getLevel(), 0);
			GrozLogger.logGame("HP: " + monster.getHP(), 0);
		}
		GrozLogger.logGame("Health: " + monster.getHealth(), .5);
		if (Groz.DEBUG)
		{
			GrozLogger.logGame("ATK: " + monster.getAttack(), 0);
			GrozLogger.logGame("DEF: " + monster.getDefense(), 0);
			GrozLogger.logGame("SPD: " + monster.getSpeed(), 0);
			GrozLogger.logGame("AGL: " + monster.getAgility(), 0);
			GrozLogger.logGame(Level.INFO, "Is Player null:" + (this.player == null), false, 0);
		}
		GrozLogger.logGame("", 0);
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
			GrozLogger.logGame("", 0);
			GrozLogger.logGame("Attacker: " + attacker.getEntity().getName(), .5);
			GrozLogger.logGame(attacker.getEntity().getName() + ": " + attacker.getHealth(), .5);
			GrozLogger.logGame(victim.getEntity().getName() + ": " + victim.getHealth(), .5);
			GrozLogger.logGame(attacker.getEntity().getName() + " is paralyzed!", .5);
		}
		attacker.setHealth((int) attacker.preformEffect(EnumStatType.Health, attacker.getHealth()));
	}
	
	public void doTurn()
	{
		if (this.monster.preformEffect(EnumStatType.SPD, this.monster.getSpeed()) <= this.player.preformEffect(EnumStatType.SPD, this.player.getSpeed()))
		{
			if (!this.monster.isDead() && !this.player.isDead())
			{
				this.Attack(this.player, InteractiveHelper.getAttackIndex(this.player), this.monster);
			}
			if (!this.monster.isDead() && !this.player.isDead())
			{
				this.Attack(this.monster, 0, this.player);
			}
		}
		else
		{
			if (!this.monster.isDead() && !this.player.isDead())
			{
				this.Attack(this.monster, 0, this.player);
			}
			if (!this.monster.isDead() && !this.player.isDead())
			{
				this.Attack(this.player, InteractiveHelper.getAttackIndex(this.player), this.monster);
			}
		}
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

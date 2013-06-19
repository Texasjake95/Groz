package groz;

import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.InGamePlayer;
import groz.entity.Player;
import groz.entity.attack.Attack;
import groz.util.InteractiveHelper;
import groz.util.logging.GrozLogger;
import groz.zone.ZoneBase;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * The main class of Groz
 * 
 * @author Texasjake95
 */
public class Groz {
	
	private static Player player;
	private static InGamePlayer gamePlayer;
	private static Random rand = new Random();
	public static Scanner mainScn;
	public static final boolean DEBUG = false;
	private static String version = "@VERSION@";
	
	public static void main(String[] args) throws SecurityException, IOException
	{
		mainScn = new Scanner(System.in);
		GrozLogger.logGame("Logger Initallized");
		GrozLogger.logGame(String.format("Groz Version: %s has started up", getVersion()));
		GrozLogger.logGame("");
		ZoneBase zone = null;
		Ref.initStatTypes();
		player = new Player("Zilx", 1);
		player.setLevel(1);
		player.getStats().addHP(5);
		player.addAttack(new Attack(1d, .75d, "Normal"), 0);
		player.addAttack(new Attack(1d, .75d, "Strong"), 1);
		player.addAttack(new Attack(.5d, 1d, "Light"), 2);
		player.addAttack(new Attack(1d, .75d, "Heal").setGivesDamage(false), 3);
		gamePlayer = new InGamePlayer(player, 1);
		for (int i = 0; i < 10; i++)
		{
			if (!gamePlayer.isDead())
			{
				zone = ZoneBase.randomZone.get(rand.nextInt(ZoneBase.randomZone.size()));
				Battle battle = new Battle(gamePlayer, zone);
				while (battle.canContinue())
				{
					if (battle.monster.preformEffect(EnumStatType.SPD, battle.monster.getSpeed()) <= gamePlayer.preformEffect(EnumStatType.SPD, gamePlayer.getSpeed()))
					{
						if (!battle.monster.isDead() && !gamePlayer.isDead())
						{
							battle.Attack(gamePlayer, InteractiveHelper.getAttackIndex(gamePlayer), battle.monster);
						}
						if (!battle.monster.isDead() && !gamePlayer.isDead())
						{
							battle.Attack(battle.monster, 0, gamePlayer);
						}
					}
					else
					{
						if (!battle.monster.isDead() && !gamePlayer.isDead())
						{
							battle.Attack(battle.monster, 0, gamePlayer);
						}
						if (!battle.monster.isDead() && !gamePlayer.isDead())
						{
							battle.Attack(gamePlayer, InteractiveHelper.getAttackIndex(gamePlayer), battle.monster);
						}
					}
					gamePlayer = battle.player;
				}
				GrozLogger.logGame("");
				GrozLogger.logGame("");
				GrozLogger.logGame("");
				GrozLogger.logGame("");
			}
			
		}
		mainScn.close();
		GrozLogger.logGame("Player health is " + gamePlayer.getHealth());
	}

	private static String getVersion()
	{
		if(!version.equals("@VER" + "SION@"))
			return version;
		return "Local Build";
	}
}

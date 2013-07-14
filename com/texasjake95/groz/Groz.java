package com.texasjake95.groz;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import com.texasjake95.groz.entity.InGamePlayer;
import com.texasjake95.groz.entity.Player;
import com.texasjake95.groz.entity.attack.Attack;
import com.texasjake95.groz.util.Reference;
import com.texasjake95.groz.util.SaveFile;
import com.texasjake95.groz.util.cheat.Cheat;
import com.texasjake95.groz.util.cheat.CheatGod;
import com.texasjake95.groz.util.logging.GrozLogger;
import com.texasjake95.groz.zone.ZoneBase;

/**
 * The main class of Groz
 * 
 * @author Texasjake95
 */
public class Groz {
	
	private static Player player;
	public static InGamePlayer gamePlayer;
	private static Random rand = new Random();
	public static Scanner mainScn;
	public static final boolean DEBUG = false;
	private static String version = "@VERSION@";
	public static SaveFile saveFile;
	public static String[] cheats;
	
	public static void main(String[] args) throws SecurityException, IOException
	{
		mainScn = new Scanner(System.in);
		GrozLogger.logGame("Logger Initallized", .25);
		GrozLogger.logGame(String.format("Groz Version: %s has started up", getVersion()), .25);
		GrozLogger.logGame("", .25);
		initCheats();
		cheats = Cheat.Names();
		saveFile = new SaveFile();
		if (args.length > 0)
		{
			if (saveFile.doesSaveFileHave("String.playerName"))
			{
				player = saveFile.createPlayer();
			}
			else
			{
				player = new Player(args[0], 1);
				player.addAttack(new Attack(1d, .75d, "Normal"), 0);
				player.addAttack(new Attack(1d, .75d, "Strong"), 1);
				player.addAttack(new Attack(.5d, 1d, "Light"), 2);
				player.addAttack(new Attack(1d, .75d, "Heal").setGivesDamage(false), 3);
			}
		}
		else
		{
			if (saveFile.doesSaveFileHave("String.playerName"))
			{
				player = saveFile.createPlayer();
			}
			else
			{
				player = new Player("Zilx", 1);
				player.addAttack(new Attack(1d, .75d, "Normal"), 0);
				player.addAttack(new Attack(1d, .75d, "Strong"), 1);
				player.addAttack(new Attack(.5d, 1d, "Light"), 2);
				player.addAttack(new Attack(1d, .75d, "Heal").setGivesDamage(false), 3);
			}
		}
		saveFile.save();
		ZoneBase zone = null;
		Reference.initStatTypes();
		if (!saveFile.doesSaveFileHave("Integer.playerHealth"))
		{
			saveFile.setInt("playerHealth", player.getStats().getMaxHealth());
		}
		gamePlayer = new InGamePlayer(player, saveFile.getInt("playerLevel"));
		gamePlayer.setHealth(saveFile.getInt("playerHealth"));
		for (int i = 0; i < 10; i++)
		{
			if (!gamePlayer.isDead())
			{
				zone = ZoneBase.randomZone.get(rand.nextInt(ZoneBase.randomZone.size()));
				Battle battle = new Battle(gamePlayer, zone);
				while (battle.canContinue())
				{
					battle.doTurn();
					gamePlayer = battle.player;
				}
				saveFile.save();
				GrozLogger.logGame("", 0);
				GrozLogger.logGame("", 0);
				GrozLogger.logGame("", 0);
				GrozLogger.logGame("", 0);
			}
		}
		mainScn.close();
		GrozLogger.logGame("Player health is " + gamePlayer.getHealth(), 5);
	}
	
	private static void initCheats()
	{
		new CheatGod();
	}
	
	private static String getVersion()
	{
		if (!version.equals("@VER" + "SION@"))
			return version;
		return "Local Build";
	}
	
	public static void activateCheat(String name)
	{
		Cheat.getCheatfromName(name).activateCheat();
	}
}

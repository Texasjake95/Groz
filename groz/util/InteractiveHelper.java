package groz.util;

import groz.Groz;
import groz.entity.InGamePlayer;
import groz.util.logging.GrozLogger;

public class InteractiveHelper {
	
	public static int getAttackIndex(InGamePlayer player)
	{
		GrozLogger.logGame("Available Attacks:", 1);
		for (int index = 0; index < player.getEntity().getAttacks().length; index++)
			GrozLogger.logGame(player.getEntity().getAttack(index).getName(), .5);
		while (true)
		{
			String name = getNextLine();
			for (int index = 0; index < player.getEntity().getAttacks().length; index++)
				if (player.getEntity().getAttack(index).getName().equalsIgnoreCase(name))
				{
					GrozLogger.logGame("", 0);
					GrozLogger.logInput("Player has picked Attack: " + name);
					return index;
				}
			GrozLogger.logGame("Invalid name please specify another", .5);
		}
	}
	
	private static String getNextLine()
	{
		String command = null;
		while(command == null)
		{
			command = Groz.mainScn.nextLine();
			for(String cheat : Groz.cheats)
			{
				if(cheat.compareTo(command) == 0)
				{
					Groz.activateCheat(command);
					command = null;
				}
			}
		}
		return command;
	}
	
	public static void gameWait_Mill(double time)
	{
		long start = System.currentTimeMillis();
		long current = 0;
		while (current - start < time)
		{
			current = System.currentTimeMillis();
		}
	}
	
	public static void gameWait_Sec(double time)
	{
		gameWait_Mill(time * 1000);
	}
	
	public static void gameWait_Min(double time)
	{
		gameWait_Sec(time * 60);
	}
}

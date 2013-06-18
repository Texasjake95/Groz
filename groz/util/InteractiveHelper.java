package groz.util;

import groz.Groz;
import groz.entity.InGamePlayer;
import groz.util.logging.GrozLogger;

public class InteractiveHelper {
	
	public static int getAttackIndex(InGamePlayer player)
	{
		GrozLogger.logGame("Available Attacks:");
		for (int index = 0; index < player.getEntity().getAttacks().length; index++)
			GrozLogger.logGame(player.getEntity().getAttack(index).getName());
		while (true)
		{
			String name = Groz.mainScn.nextLine();
			for (int index = 0; index < player.getEntity().getAttacks().length; index++)
				if (player.getEntity().getAttack(index).getName().equalsIgnoreCase(name))
				{
					GrozLogger.logGame("");
					GrozLogger.logInput("Player has picked Attack: " + name);
					return index;
				}
			GrozLogger.logGame("Invalid name please specify another");
		}
	}
}

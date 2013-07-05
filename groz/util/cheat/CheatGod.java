package groz.util.cheat;

import groz.Groz;

public class CheatGod extends Cheat {
	
	public CheatGod()
	{
		super("GODMODE");
	}
	
	@Override
	public void activateCheat()
	{
		if (Groz.gamePlayer != null)
		{
			Groz.gamePlayer.setGodStats();
			cheatsActive = true;
		}
	}
}

package com.texasjake95.groz.util.cheat;

import com.texasjake95.groz.Groz;

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

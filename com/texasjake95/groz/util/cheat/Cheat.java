package com.texasjake95.groz.util.cheat;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Cheat {
	
	private static final ArrayList<Cheat> cheatList = new ArrayList<Cheat>();
	public static boolean cheatsActive = false;
	private String name;
	
	public Cheat(String name)
	{
		this.name = name;
		addCheat(this);
	}
	
	public abstract void activateCheat();
	
	public static String[] Names()
	{
		Iterator<Cheat> cheats = cheatList.iterator();
		int x = 0;
		String[] Names = new String[cheatList.size()];
		while (cheats.hasNext())
		{
			Names[x] = cheats.next().name;
			x++;
		}
		return Names;
	}
	
	public static Cheat getCheatfromName(String name)
	{
		Iterator<Cheat> cheats = cheatList.iterator();
		Cheat cheat = null;
		while (cheats.hasNext())
		{
			cheat = cheats.next();
			if (cheat.name.compareTo(name) == 0)
				break;
		}
		return cheat;
	}
	
	public static void addCheat(Cheat cheat)
	{
		cheatList.add(cheat);
	}
}

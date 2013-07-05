package groz.entity;

import java.util.logging.Level;

import groz.Groz;
import groz.util.logging.GrozLogger;

public class InGamePlayer extends InGameEntity {
	
	public static boolean godMode = false;
	
	public InGamePlayer(Player player, int lvl)
	{
		super(player, lvl);
	}
	
	public void save()
	{
		if(!godMode)
		{
		Groz.saveFile.setInt("playerAttack", this.ATK);
		Groz.saveFile.setInt("playerDefense", this.DEF);
		Groz.saveFile.setInt("playerAgility", this.AGL);
		Groz.saveFile.setInt("playerHP", this.HP);
		Groz.saveFile.setInt("playerSpeed", this.SPD);
		Groz.saveFile.setInt("playerLevel",this.lvl);
		Groz.saveFile.setInt("playerHealth",this.Health);
		}
		
	}
	
	public void setGodStats()
	{
		this.HP = this.AGL = this.ATK = this.DEF = this.SPD = 999;
		this.Health = this.getMaxHealth();
		godMode = true;
		GrozLogger.logGame(Level.INFO, "God Mode Active", true, .1);
	}
}

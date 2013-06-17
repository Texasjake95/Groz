package groz.entity;

import groz.Ref;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.zone.ZoneBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DynMonster extends InGameEntity {
	
	protected static final Random rand = new Random();
	
	public DynMonster(Monster monster, int lvl)
	{
		super(monster, lvl);
	}
	
	public static DynMonster getDynLvl(InGamePlayer player, ZoneBase zone)
	{
		ArrayList<Monster> list = zone.getMonsterList();
		Collections.shuffle(list);
		Monster monster = list.get(rand.nextInt(list.size()));
		DynMonster dynMonster = new DynMonster(monster, getLvl(player, zone));
		dynMonster.setStats();
		return dynMonster;
	}
	
	public void setStats()
	{
		ArrayList<EnumStatType> stats = new ArrayList<EnumStatType>();
		stats.addAll(this.getMonster().type.getStats());
		stats.addAll(Ref.BaseStats);
		for (int i = 1; i < this.lvl; i++)
		{
			for (int j = 0; j <= 2; j++)
			{
				Collections.shuffle(stats);
				int x = rand.nextInt(stats.size());
				addStat(stats.get(x));
			}
		}
	}
	
	private void addStat(EnumStatType type)
	{
		switch (type)
		{
			case HP:
			{
				this.HP += 1;
				this.MaxHealth += 5;
				this.Health = this.MaxHealth;
				break;
			}
			case ATK:
				this.ATK += 1;
				break;
			case DEF:
				this.DEF += 1;
				break;
			case SPD:
				this.SPD += 1;
				break;
			case AGL:
				this.AGL += 1;
				break;
			default:
				break;
		}
	}
	
	public static int getLvl(InGamePlayer player, ZoneBase zone)
	{
		if (player.getLevel() >= zone.getRecommendedLvl())
		{
			if (player.getLevel() > 2)
			{
				if (rand.nextDouble() < .50d)
				{
					int lvl = player.getLevel() - rand.nextInt(2);
					return lvl < 1 ? 1 : lvl;
				}
				else
				{
					return player.getLevel() + rand.nextInt(2);
				}
			}
			else
			{
				return rand.nextInt(player.getLevel());
			}
		}
		else
		{
			if (rand.nextDouble() < .50d)
			{
				int lvl = zone.getRecommendedLvl() - rand.nextInt(2);
				return lvl < 1 ? 1 : lvl;
			}
			else
			{
				return zone.getRecommendedLvl() + rand.nextInt(2);
			}
		}
	}
	
	private Monster getMonster()
	{
		return (Monster) this.getEntity();
	}
}

package groz.entity;

import groz.util.Ref;

import java.util.ArrayList;

/**
 * This enum is used to have {@link Monster}s Level up with some stats raised
 * higher than others
 * 
 * @author Texasjake95
 */
public enum EnumMonsterType
{
	Tank(Ref.TankStats), Speed(Ref.SPDStats), Attack(Ref.ATKStats), Agility(Ref.AGLStats), Defense(Ref.DEFStats), Boss(Ref.BossStats);
	
	private ArrayList<EnumStatType> stats;
	
	EnumMonsterType(ArrayList<EnumStatType> stats)
	{
		this.stats = stats;
	}
	
	public ArrayList<EnumStatType> getStats()
	{
		return this.stats;
	}
	
	public enum EnumStatType
	{
		HP(), ATK(), DEF(), AGL(), SPD(), Health();
	}
}

package com.texasjake95.groz.entity;

import java.util.ArrayList;

import com.texasjake95.groz.util.Reference;

/**
 * This enum is used to have {@link Monster}s Level up with some stats raised
 * higher than others
 * 
 * @author Texasjake95
 */
public enum EnumMonsterType
{
	Tank(Reference.TankStats), Speed(Reference.SPDStats), Attack(Reference.ATKStats), Agility(Reference.AGLStats), Defense(Reference.DEFStats), Boss(Reference.BossStats);
	
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

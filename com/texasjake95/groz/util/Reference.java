package com.texasjake95.groz.util;

import static com.texasjake95.groz.entity.EnumMonsterType.EnumStatType.AGL;
import static com.texasjake95.groz.entity.EnumMonsterType.EnumStatType.ATK;
import static com.texasjake95.groz.entity.EnumMonsterType.EnumStatType.DEF;
import static com.texasjake95.groz.entity.EnumMonsterType.EnumStatType.HP;
import static com.texasjake95.groz.entity.EnumMonsterType.EnumStatType.SPD;

import java.util.ArrayList;

import com.texasjake95.groz.Groz;
import com.texasjake95.groz.entity.EnumMonsterType.EnumStatType;

/**
 * The {@link Groz} reference class
 * 
 * @author Texasjake95
 */
public class Reference {
	
	public static final int MAXLEVEL = 100;
	public static ArrayList<EnumStatType> BaseStats = new ArrayList<EnumStatType>();
	public static ArrayList<EnumStatType> TankStats = new ArrayList<EnumStatType>();
	public static ArrayList<EnumStatType> DEFStats = new ArrayList<EnumStatType>();
	public static ArrayList<EnumStatType> ATKStats = new ArrayList<EnumStatType>();
	public static ArrayList<EnumStatType> AGLStats = new ArrayList<EnumStatType>();
	public static ArrayList<EnumStatType> SPDStats = new ArrayList<EnumStatType>();
	public static ArrayList<EnumStatType> BossStats = new ArrayList<EnumStatType>();
	
	public static void initStatTypes()
	{
		BaseStats.add(HP);
		BaseStats.add(ATK);
		BaseStats.add(DEF);
		BaseStats.add(SPD);
		BaseStats.add(AGL);
		BaseStats.add(ATK);
		BaseStats.add(DEF);
		BaseStats.add(SPD);
		BaseStats.add(AGL);
		TankStats.add(HP);
		TankStats.add(HP);
		TankStats.add(DEF);
		DEFStats.add(DEF);
		DEFStats.add(DEF);
		DEFStats.add(ATK);
		ATKStats.add(ATK);
		ATKStats.add(ATK);
		ATKStats.add(SPD);
		AGLStats.add(AGL);
		AGLStats.add(AGL);
		AGLStats.add(SPD);
		SPDStats.add(AGL);
		SPDStats.add(AGL);
		SPDStats.add(SPD);
		SPDStats.add(ATK);
		BossStats.add(HP);
		BossStats.add(ATK);
		BossStats.add(DEF);
		BossStats.add(SPD);
		BossStats.add(AGL);
		BossStats.add(HP);
		BossStats.add(ATK);
		BossStats.add(DEF);
		BossStats.add(SPD);
		BossStats.add(AGL);
	}
}

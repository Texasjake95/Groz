package groz;

import static groz.entity.EnumMonsterType.EnumStatType.AGL;
import static groz.entity.EnumMonsterType.EnumStatType.ATK;
import static groz.entity.EnumMonsterType.EnumStatType.DEF;
import static groz.entity.EnumMonsterType.EnumStatType.HP;
import static groz.entity.EnumMonsterType.EnumStatType.SPD;
import groz.entity.EnumMonsterType.EnumStatType;

import java.util.ArrayList;

/**
 * The {@link Groz} reference class
 * 
 * @author Texasjake95
 */
public class Ref {
	
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

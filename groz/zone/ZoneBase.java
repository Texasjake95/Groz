package groz.zone;

import groz.entity.Monster;
import groz.util.IntPair;

import java.util.ArrayList;

/**
 * @author Texasjake95
 */
public class ZoneBase {
	
	public static ArrayList<ZoneBase> randomZone = new ArrayList<ZoneBase>();
	private ArrayList<Monster> monsterList;
	private IntPair cords;
	private int lvl;
	private boolean hasDungen;
	public static final ZoneBase[][] zoneList = new ZoneBase[12][8];
	public static final ZoneBase plainZone = new ZoneBase(new IntPair(0, 0), ZoneRef.plainZone.getMonsterList(), 0);
	public static final ZoneBase forestZone = new ZoneBase(new IntPair(0, 1), ZoneRef.forestZone.getMonsterList(), 0);
	public static final ZoneBase mountainZone = new ZoneBase(new IntPair(0, 2), ZoneRef.maintainZone.getMonsterList(), 0);
	
	public ZoneBase(IntPair pair, ArrayList<Monster> monsterList, int recommendLvl)
	{
		if (zoneList[pair.getIntX()][pair.getIntY()] != null)
		{
			System.out.println("There is already a Zone located at" + pair.getIntX() + ", " + pair.getIntY());
			throw new ArrayIndexOutOfBoundsException();
		}
		this.cords = pair;
		this.monsterList = monsterList;
		randomZone.add(this);
		this.setLvl(recommendLvl);
	}
	
	public ArrayList<Monster> getMonsterList()
	{
		return this.monsterList;
	}
	
	public int getRecommendedLvl()
	{
		return lvl;
	}
	
	public boolean hasDungen()
	{
		return this.hasDungen;
	}
	
	public ZoneBase setDungen(boolean has)
	{
		this.hasDungen = has;
		return this;
	}
	
	public void setLvl(int lvl)
	{
		this.lvl = lvl;
	}
	
	public int xCord()
	{
		return this.cords.getIntX();
	}
	
	public int yCord()
	{
		return this.cords.getIntY();
	}
}

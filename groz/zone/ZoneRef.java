package groz.zone;

import groz.entity.Monster;

/**
 * @author Texasjake95
 */
public class ZoneRef {
	
	public static MonsterList plainZone = new MonsterList().addMonster(Monster.bat, 4).addMonster(Monster.fairy, 3).addMonster(Monster.rat, 5).addMonster(Monster.wolf, 2);
	public static MonsterList forestZone = new MonsterList().addMonster(Monster.zombie, 2).addMonster(Monster.rat, 3).addMonster(Monster.fairy, 1).addMonster(Monster.wolf, 3);
	public static MonsterList maintainZone = new MonsterList().addMonster(Monster.bat, 3).addMonster(Monster.zombie, 2).addMonster(Monster.golem, 3).addMonster(Monster.dwarf, 2);
}

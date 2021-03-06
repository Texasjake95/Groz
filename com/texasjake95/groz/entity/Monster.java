package com.texasjake95.groz.entity;

import static com.texasjake95.groz.entity.EnumMonsterType.Agility;
import static com.texasjake95.groz.entity.EnumMonsterType.Attack;
import static com.texasjake95.groz.entity.EnumMonsterType.Defense;
import static com.texasjake95.groz.entity.EnumMonsterType.Speed;
import static com.texasjake95.groz.entity.EnumMonsterType.Tank;

import com.texasjake95.groz.Stats;
import com.texasjake95.groz.entity.attack.Attack;
import com.texasjake95.groz.util.logging.GrozLogger;

/**
 * This class is the base for all monsters
 * 
 * @author Texasjake95
 */
public class Monster extends GrozEntity {
	
	public int ID;
	protected EnumMonsterType type;
	public static final Monster[] monsterList = new Monster[200];
	public static final Monster bat = new Monster("Bat", new Stats(0, 15, 3, 2, 7, 4), 0, Speed);
	public static final Monster zombie = new Monster("Zombie", new Stats(0, 13, 5, 2, 7, 4), 1, Attack);
	public static final Monster wolf = new Monster("Wolf", new Stats(0, 12, 3, 2, 5, 4), 2, Speed);
	public static final Monster golem = new Monster("Golem", new Stats(0, 20, 2, 6, 1, 1), 3, Defense);
	public static final Monster rat = new Monster("Rat", new Stats(0, 8, 2, 3, 3, 2), 4, Agility);
	public static final Monster fairy = new Monster("Fairy", new Stats(0, 12, 3, 3, 3, 4), 5, Agility);
	public static final Monster dwarf = new Monster("Dwarf", new Stats(0, 13, 3, 4, 1, 2), 6, Tank);
	
	public Monster(String name, Stats stats, int id, EnumMonsterType type)
	{
		super(name, stats);
		if (monsterList[id] != null)
		{
			GrozLogger.logGame("While attempting to add " + this.getName() + ", the slot is occupied by " + monsterList[id].getName(), 0);
			throw new ArrayIndexOutOfBoundsException();
		}
		else
		{
			this.ID = id;
			this.type = type;
			monsterList[id] = this;
		}
	}
	
	@Override
	public Monster addAttack(Attack attack, int index)
	{
		return (Monster) super.addAttack(attack, index);
	}
	/*
	 * public Monster addAttacks(Attack[] attacks) { for (Attack attack :
	 * attacks) { this.addAttack(attack); } return this; }
	 */
}

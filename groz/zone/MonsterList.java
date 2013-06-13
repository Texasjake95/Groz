package groz.zone;

import groz.entity.Monster;

import java.util.ArrayList;

/**
 * @author Texasjake95
 */
public class MonsterList {

	private ArrayList<Monster> monsterList;

	public MonsterList() {
		this.monsterList = new ArrayList<Monster>();
	}

	public MonsterList addMonster(Monster monster, int times) {
		for (int i = 0; i < times; i++) {
			monsterList.add(monster);
		}
		return this;
	}

	public ArrayList<Monster> getMonsterList() {
		return this.monsterList;
	}
}

package groz.entity;

import static groz.entity.EnumMonsterType.Agility;
import static groz.entity.EnumMonsterType.Attack;
import static groz.entity.EnumMonsterType.Defense;
import static groz.entity.EnumMonsterType.Speed;
import static groz.entity.EnumMonsterType.Tank;
import groz.DualStats;
import groz.Ref;
import groz.Stats;
import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.attack.Attack;
import groz.zone.ZoneBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class is the base for all monsters
 * 
 * @author Texasjake95
 */
public class Monster extends Entity {

	public int ID;
	private EnumMonsterType type;
	private static Random rand = new Random();
	public static final Monster[] monsterList = new Monster[200];
	public static final Monster bat = new Monster("Bat", new DualStats(
			new Stats(0, 15, 3, 2, 7, 4, 1)), 0, Speed);
	public static final Monster zombie = new Monster("Zombie", new DualStats(
			new Stats(0, 13, 5, 2, 7, 4, 1)), 1, Attack);
	public static final Monster wolf = new Monster("Wolf", new DualStats(
			new Stats(0, 12, 3, 2, 5, 4, 1)), 2, Speed);
	public static final Monster golem = new Monster("Golem", new DualStats(
			new Stats(0, 20, 2, 6, 1, 1, 1)), 3, Defense);
	public static final Monster rat = new Monster("Rat", new DualStats(
			new Stats(0, 8, 2, 3, 3, 2, 1)), 4, Agility);
	public static final Monster fairy = new Monster("Fairy", new DualStats(
			new Stats(0, 12, 3, 3, 3, 4, 1)), 5, Agility);
	public static final Monster dwarf = new Monster("Dwarf", new DualStats(
			new Stats(0, 13, 3, 4, 1, 2, 1)), 6, Tank);

	public static Monster getDynLvl(Player player, ZoneBase zone) {
		ArrayList<Monster> list = zone.getMonsterList();
		Collections.shuffle(list);
		Monster monster = list.get(rand.nextInt(list.size()));
		monster.setLvl(player, zone);
		monster.setStats();
		return monster;
	}

	public Monster(String name, DualStats stats, int id, EnumMonsterType type) {
		this(name, stats, id, type, false);
	}

	public Monster(String name, DualStats stats, int id, EnumMonsterType type,
			boolean isCopy) {
		super(name, stats);
		if (!isCopy) {
			if (monsterList[id] != null) {
				System.out.println("While attempting to add " + this.getName()
						+ ", the slot is occupied by "
						+ monsterList[id].getName());
				throw new ArrayIndexOutOfBoundsException();
			} else {
				this.ID = id;
				monsterList[id] = this;
			}
		}
		this.type = type;
	}

	private void addStat(EnumStatType type) {
		switch (type) {
		case HP:
			this.getStats().addHP(1);
			break;
		case ATK:
			this.getStats().addAttack(1);
			break;
		case DEF:
			this.getStats().addDefense(1);
			break;
		case SPD:
			this.getStats().addSpeed(1);
			break;
		case AGL:
			this.getStats().addAgility(1);
			break;
		default:
			break;
		}
	}

	public Monster copy() {
		return new Monster(this.getName(), this.getStats(), this.ID, this.type,
				true);
	}

	public void setLvl(Player player, ZoneBase zone) {
		if (player.getStats().getLvl() >= zone.getRecommendedLvl()) {
			if (player.getStats().getLvl() > 2) {
				if (rand.nextDouble() < .50d) {
					this.getStats().setLvl(
							player.getStats().getLvl() - rand.nextInt(2));
				} else {
					this.getStats().setLvl(
							player.getStats().getLvl() + rand.nextInt(2));
				}
			} else {
				this.getStats()
						.setLvl(rand.nextInt(player.getStats().getLvl()));
			}
		} else {
			if (rand.nextDouble() < .50d) {
				this.getStats().setLvl(
						zone.getRecommendedLvl() - rand.nextInt(2));
			} else {
				this.getStats().setLvl(
						zone.getRecommendedLvl() + rand.nextInt(2));
			}
		}
	}

	private void setMaxHealth_A() {
		this.getStats().setMaxHealth(
				this.getStats().getBaseHealth() + this.getStats().getHP() * 5);
		this.setHealth(this.getStats().getMaxHealth());
	}

	public void setStats() {
		ArrayList<EnumStatType> stats = new ArrayList<EnumStatType>();
		stats.addAll(this.type.getStats());
		stats.addAll(Ref.BaseStats);
		for (int i = 1; i < this.getStats().getLvl(); i++) {
			for (int j = 0; j <= 2; j++) {
				Collections.shuffle(stats);
				int x = rand.nextInt(stats.size());
				addStat(stats.get(x));
			}
		}
		setMaxHealth_A();
	}

	@Override
	public Monster addAttack(Attack attack) {
		// TODO Auto-generated method stub
		return (Monster) super.addAttack(attack);
	}

	public Monster addAttacks(Attack[] attacks) {
		for (Attack attack : attacks) {
			this.addAttack(attack);
		}
		return this;
	}
}

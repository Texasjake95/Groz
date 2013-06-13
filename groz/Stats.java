package groz;

/**
 * This class is used to calculated the stats of any {@link Entity}, there are
 * normal two per {@link Entity} one for Base Stats and one for the Level Up
 * stats.
 * 
 * @author Texasjake95
 */
public class Stats {

	protected static int MAXLEVEL = 100;
	private int MaxHealth;
	private int Health;
	private int HP;
	private int ATK;
	private int DEF;
	private int SPD;
	private int AGL;
	private int Lvl;
	private int BaseHealth;

	public Stats(int HP, int BaseHealth, int ATK, int DEF, int SPD, int AGL,
			int LVL) {
		this.setHP(HP);
		this.setBaseHealth(BaseHealth);
		this.ATK = ATK;
		this.DEF = DEF;
		this.SPD = SPD;
		this.AGL = AGL;
		this.Lvl = LVL;
	}

	public void addAgility(int add) {
		if ((this.AGL + add) >= MAXLEVEL) {
			this.AGL = MAXLEVEL;
		} else {
			this.AGL += add;
		}
	}

	public void addAttack(int add) {
		if ((this.ATK + add) >= MAXLEVEL) {
			this.ATK = MAXLEVEL;
		} else {
			this.ATK += add;
		}
	}

	public void addDefense(int add) {
		if ((this.DEF + add) >= MAXLEVEL) {
			this.DEF = MAXLEVEL;
		} else {
			this.DEF += add;
		}
	}

	public void addHP(int add) {
		if ((this.getHP() + add) >= MAXLEVEL) {
			this.setHP(MAXLEVEL);
		} else {
			this.setHP(this.getHP() + add);
		}
		this.setMaxHealth(((this.getHP() * 5) + this.BaseHealth));
	}

	public void addSpeed(int add) {
		if ((this.SPD + add) >= MAXLEVEL) {
			this.SPD = MAXLEVEL;
		} else {
			this.SPD += add;
		}
	}

	public int getAgility() {
		return this.AGL;
	}

	public int getAttack() {
		return this.ATK;
	}

	public int getBaseHealth() {
		return BaseHealth;
	}

	public int getDefense() {
		return this.DEF;
	}

	public int getHealth() {
		return Health;
	}

	public int getHP() {
		return HP;
	}

	public int getLvl() {
		return Lvl;
	}

	public int getMaxHealth() {
		this.setMaxHealth(((this.getHP() * 5) + this.BaseHealth));
		return MaxHealth;
	}

	public int getSpeed() {
		return this.SPD;
	}

	public void setBaseHealth(int baseHealth) {
		BaseHealth = baseHealth;
	}

	public void setHealth(int health) {
		if (health >= this.getMaxHealth()) {
			Health = this.getMaxHealth();
		} else {
			this.Health = health;
		}
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public void setLvl(int lvl) {
		Lvl = lvl;
		if (Lvl == 0) {
			Lvl = 1;
		}
	}

	public void setMaxHealth(int health) {
		MaxHealth = health;
	}

	public void subtractHealth(int health) {
		if (this.Health - health <= 0) {
			this.Health = 0;
			return;
		}
		if (health > 0) {
			this.Health -= health;
			return;
		} else {
			this.Health -= 1;
			return;
		}
	}

	public void addHealth(int health) {
		this.Health += health;
	}
}
package groz;

import groz.entity.Monster;
import groz.entity.Player;

/**
 * This class is used to allow {@link Monster}s to created dynamiclly by using a
 * second {@link Stats}, One is the Base Stats while the other is used when an
 * {@link Entity}, be it the {@link Player} or {@link Monster} levels up and the
 * changed stats are then stored there.
 * 
 * @author Texasjake95
 */
public class DualStats {

	private Stats LvlStats;
	private Stats BaseStats;

	public DualStats(Stats stats) {
		this.BaseStats = stats;
		this.resetStats();
	}

	public void addAgility(int add) {
		this.LvlStats.addAgility(add);
	}

	public void addAttack(int add) {
		this.LvlStats.addAttack(add);
	}

	public void addDefense(int add) {
		this.LvlStats.addDefense(add);
	}

	public void addHP(int add) {
		this.LvlStats.addHP(add);
	}

	public void addSpeed(int add) {
		this.LvlStats.addSpeed(add);
	}

	public int getAgility() {
		if (this.LvlStats.getAgility() + this.BaseStats.getAgility() < Ref.MAXLEVEL) {
			return this.LvlStats.getAgility() + this.BaseStats.getAgility();
		} else {
			return Ref.MAXLEVEL;
		}
	}

	public int getAttack() {
		if (this.LvlStats.getAttack() + this.BaseStats.getAttack() < Ref.MAXLEVEL) {
			return this.LvlStats.getAttack() + this.BaseStats.getAttack();
		} else {
			return Ref.MAXLEVEL;
		}
	}

	public int getBaseHealth() {
		return this.BaseStats.getBaseHealth();
	}

	public Stats getBaseStats() {
		return this.BaseStats;
	}

	public int getDefense() {
		if (this.LvlStats.getDefense() + this.BaseStats.getDefense() < Ref.MAXLEVEL) {
			return this.LvlStats.getDefense() + this.BaseStats.getDefense();
		} else {
			return Ref.MAXLEVEL;
		}
	}

	public int getHealth() {
		return this.LvlStats.getHealth();
	}

	public int getHP() {
		if (this.LvlStats.getHP() + this.BaseStats.getHP() < Ref.MAXLEVEL) {
			return this.LvlStats.getHP() + this.BaseStats.getHP();
		} else {
			return Ref.MAXLEVEL;
		}
	}

	public int getLvl() {
		return this.LvlStats.getLvl() + 1;
	}

	public Stats getLvlStats() {
		return this.LvlStats;
	}

	public int getMaxHealth() {
		return this.LvlStats.getMaxHealth() + this.BaseStats.getBaseHealth();
	}

	public int getSpeed() {
		if (this.LvlStats.getSpeed() + this.BaseStats.getSpeed() < Ref.MAXLEVEL) {
			return this.LvlStats.getSpeed() + this.BaseStats.getSpeed();
		} else {
			return Ref.MAXLEVEL;
		}
	}

	public void resetStats() {
		this.LvlStats = new Stats(0, 0, 0, 0, 0, 0, 0);
	}

	public void setHealth(int health) {
		this.LvlStats.setHealth(health);
	}

	public void setHP(int hP) {
		this.LvlStats.setHP(hP);
	}

	public void setLvl(int lvl) {
		this.LvlStats.setLvl(lvl - 1);
	}

	public void setMaxHealth(int health) {
		this.LvlStats.setMaxHealth(health);
	}

	public void subtractHealth(int health) {
		this.LvlStats.subtractHealth(health);
	}

	public void addHealth(int health) {
		if (this.LvlStats.getHealth() + health >= this.getMaxHealth()) {
			this.LvlStats.setHealth(this.getMaxHealth());
			return;
		} else {
			this.LvlStats.addHealth(health);
			return;
		}
	}
}

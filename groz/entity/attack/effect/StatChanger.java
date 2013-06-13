package groz.entity.attack.effect;

import groz.entity.EnumMonsterType.EnumStatType;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Texasjake95
 */
public class StatChanger {

	Map<EnumStatType, StatModifier> stat = new TreeMap<EnumStatType, StatModifier>();

	public StatChanger() {
	}

	public StatChanger addStats(EnumStatType type, EnumModifier mod, double d) {
		return this.addStats(new StatModifier(type, mod, d));
	}

	public StatChanger addStats(StatModifier stat) {
		this.stat.put(stat.stat, stat);
		return this;
	}

	public enum EnumModifier {
		Multiply(), Divide(), Add(), Subtract();
	}

	/** @author Texasjake95 */
	public class StatModifier {

		private EnumStatType stat;
		private EnumModifier modify;
		private double value;

		public StatModifier(EnumStatType stat, EnumModifier modify, double d) {
			this.stat = stat;
			this.modify = modify;
			this.setValue(d);
		}

		public double getValue() {
			return this.value;
		}

		public EnumStatType getStat() {
			return this.stat;
		}

		public EnumModifier getModifier() {
			return this.modify;
		}

		public void setValue(double value) {
			this.value = value;
		}
	}
}

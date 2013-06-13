package groz.util;

/**
 * @author Texasjake95
 */
public class IntPair implements Comparable<Object> {

	private int intX;
	private int intY;

	public IntPair(int int1, int int2) {
		this.intX = int1;
		this.intY = int2;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof IntPair) {
			if (this.getIntX() == ((IntPair) arg0).getIntX()
					&& this.getIntY() == ((IntPair) arg0).getIntY()) {
				return 0;
			}
		}
		return -1;
	}

	public int getIntX() {
		return this.intX;
	}

	public int getIntY() {
		return this.intY;
	}
}

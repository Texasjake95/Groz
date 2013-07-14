package com.texasjake95.groz.util;

import java.io.Serializable;

public class IntPair implements Comparable<IntPair>, Serializable {
	
	private int intX;
	private int intY;
	
	public IntPair(int int1, int int2)
	{
		this.intX = int1;
		this.intY = int2;
	}
	
	public int getIntX()
	{
		return this.intX;
	}
	
	public int getIntY()
	{
		return this.intY;
	}
	
	@Override
	public int compareTo(IntPair pair)
	{
		return this.getIntX() == pair.getIntX() ? this.getIntY() - pair.getIntY() : this.getIntX() - pair.getIntX();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof IntPair))
		{
			return false;
		}
		IntPair intPair = (IntPair) obj;
		return this.compareTo(intPair) == 0;
	}
	
	private static final long serialVersionUID = -5044528796135360263L;
}
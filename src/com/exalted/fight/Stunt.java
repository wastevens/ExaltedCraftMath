package com.exalted.fight;

public enum Stunt {

	ZERO(0, 0, 0),
	ONE(2, 0, 0),
	TWO(2, 1, 2),
	THREE(2, 2, 3);
	
	public final int dice;
	public final int successes;
	public final int staticValue;

	private Stunt(int dice, int successes, int staticValue) {
		this.dice = dice;
		this.successes = successes;
		this.staticValue = staticValue;
	}
	
	public static Stunt stunt() {
		int roll = Roller.rollDie(1000);
		if(roll == 1000) {
			return Stunt.THREE;
		}
		if(roll > 990) {
			return Stunt.ZERO;
		}
		if(roll > 900) {
			return Stunt.TWO;
		}
		return Stunt.ONE;
	}
	
	public String display() {
		return "level " + name() + " stunt";
	}
	
}

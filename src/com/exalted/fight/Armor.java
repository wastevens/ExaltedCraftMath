package com.exalted.fight;

public enum Armor {
	MORTAL_LIGHT(3, 0),
	MORTAL_MEDIUM(5, 0),
	MORTAL_HEAVY(7, 0),
	ARTIFACT_LIGHT(5, 4),
	ARTIFACT_MEDIUM(8, 7),
	ARTIFACT_HEAVY(11, 10),
	;
	
	public final int soak;
	public final int hardness;

	private Armor(int soak, int hardness) {
		this.soak = soak;
		this.hardness = hardness;
	}
}

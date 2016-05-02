package com.exalted.fight.equipment;

public enum BaseArmor implements Armor {
	MORTAL_LIGHT(3, 0),
	MORTAL_MEDIUM(5, 0),
	MORTAL_HEAVY(7, 0),
	ARTIFACT_LIGHT(5, 4),
	ARTIFACT_MEDIUM(8, 7),
	ARTIFACT_HEAVY(11, 10),
	;
	
	private final int soak;
	private final int hardness;

	private BaseArmor(int soak, int hardness) {
		this.soak = soak;
		this.hardness = hardness;
	}

	@Override
	public int soak() {
		return soak;
	}

	@Override
	public int hardness() {
		return hardness;
	}
}

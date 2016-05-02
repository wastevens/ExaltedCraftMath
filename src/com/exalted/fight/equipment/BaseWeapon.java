package com.exalted.fight.equipment;

public enum BaseWeapon {
	MORTAL_LIGHT(4, 7, 0, 1),
	MORTAL_MEDIUM(2, 9, 1, 1),
	MORTAL_HEAVY(0, 11, -1, 1),
	ARTIFACT_LIGHT(5, 10, 0, 3),
	ARTIFACT_MEDIUM(3, 12, 1, 4),
	ARTIFACT_HEAVY(1, 14, 0, 5),
	;
	
	public final int accuracy;
	public final int damage;
	public final int defense;
	public final int overwhelming;

	private BaseWeapon(int accuracy, int damage, int defense, int overwhelming) {
		this.accuracy = accuracy;
		this.damage = damage;
		this.defense = defense;
		this.overwhelming = overwhelming;
	}
}

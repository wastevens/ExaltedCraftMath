package com.exalted.fight;

import com.exalted.fight.attacks.Attack;
import com.exalted.fight.equipment.Armor;
import com.exalted.fight.equipment.BaseWeapon;
import com.exalted.fight.strategies.Strategy;

import static com.exalted.fight.Roller.roll;
import static com.exalted.fight.SuccessCounter.countIn;

public class Fighter implements Comparable<Fighter>{

	public final String name;
	public final int strength;
	public final int dexterity;
	public final int stamina;
	public final int melee;
	public final int wits;
	public final int awareness;
	public final BaseWeapon weapon;
	public final Armor armor;
	public final Strategy strategy;
	
	public int health;
	public int initaitve;
	public int crashCount;
	public int onslaught;
	
	public Fighter(String name, int strength, int dexterity, int stamina, int melee, int wits, int awareness, BaseWeapon weapon, Armor armor, Strategy strategy) {
		this.name = name;
		this.strength = strength;
		this.dexterity = dexterity;
		this.stamina = stamina;
		this.melee = melee;
		this.wits = wits;
		this.awareness = awareness;
		this.weapon = weapon;
		this.armor = armor;
		this.strategy = strategy;
		reset();
	}

	public void reset() {
		this.health = 7;
		this.crashCount = 0;
		this.initaitve = 0;
		this.onslaught = 0;
	}
	
	public int joinBattle(Stunt stunt) {
		int i = countIn(roll(wits + awareness + stunt.dice - woundPenalty())) + stunt.successes +3;
		System.out.println(name + " joins battle with a level " + stunt.name() + " stunt and gets " + i + " initiative");
		return i;
	}
	
	public Attack attackAgainst(Fighter target) {
		this.onslaught = 0;
		return strategy.toUse(this, target);
	}
	
	public boolean loseInitative(int damage) {
		this.initaitve -= damage;
		if(!isCrashed()) {
			boolean crashed = initaitve <= 0;
			if(crashed) {
				crashCount = 4;
			}
			return crashed;
		}
		return false;
	}

	public int witheringAttack(Stunt stunt) {
		return countIn(roll(dexterity + melee + weapon.accuracy + stunt.dice - woundPenalty())) + stunt.successes;
	}
	
	public int witheringDamage() {
		return strength + weapon.damage;
	}
	
	public int witheringSoak() {
		return stamina + armor.soak();
	}
	
	public int decisiveAttack(Stunt stunt) {
		return countIn(roll(dexterity + melee + stunt.dice - woundPenalty())) + stunt.successes;
	}
	
	public int decisiveDamage() {
		return initaitve;
	}
	
	public int defense(Stunt stunt) {
		return Math.max(0,  ((dexterity + melee)/2) + weapon.defense + stunt.staticValue - woundPenalty() - onslaught);
	}
	
	public void resetToBaseInitiative() {
		this.initaitve = Math.max(initaitve, 3);
	}
	
	public int woundPenalty() {
		if(health >= 6) {
			return 0;
		}
		if(health >= 4) {
			return 1;
		}
		if(health >= 2) {
			return 2;
		}
		if(health >= 1) {
			return 4;
		}
		throw new IllegalStateException("Dead fighters don't have penalties");
	}

	@Override
	public String toString() {
		String toReturn = "Name: " + name + "\n" + 
	                      "Attributes\n" + 
				          "Strength: " + strength + " Dexterity: " + dexterity + "\n" +
				          "Stamina:  " + strength + " Wits:      " + wits + "\n" +
				          "Melee:    " + melee +    " Awareness: " + awareness + "\n" +
				          "Weapon: " + weapon.name() + " Armor: " + armor.name() + "\n" +
				          "Strategy: " + strategy.name() + "\n";
		return toReturn;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Fighter)obj).name.equals(this.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public int compareTo(Fighter f) {
		return f.initaitve - this.initaitve;
	}

	public boolean loseHealth(int damage) {
		this.health -= damage;
		return health <= 0;
	}
	
	public boolean isCrashed() {
		return crashCount > 0;
	}
	
	public String status() {
		String status = name + " (I:" + initaitve + ", H:" + health;
		if(isCrashed()) {
			status += ", Crashed for " + crashCount + " rounds)";
		} else {
			status += ")";
		}
		return status;  
	}
}

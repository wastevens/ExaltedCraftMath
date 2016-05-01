package com.exalted.fight;

import static com.exalted.fight.Roller.roll;
import static com.exalted.fight.SuccessCounter.*;

public class DecisiveAttack implements Attack {

	@Override
	public boolean attack(Fighter attacker, Fighter defender) {
		Stunt attackStunt = Stunt.stunt();
		Stunt defenseStunt = Stunt.stunt();
		int attack = attacker.decisiveAttack(attackStunt);
		int defense = defender.defense(defenseStunt);
		String outcome = attacker.name + " makes a decisive attack (" + attackStunt.display() + ") against " + defender.name + " (" + defenseStunt.display() +")";
		if(attack >= defense) {
			outcome += " and hits (" + attack + " vs " + defense +")";
			outcome += " with " + attacker.initaitve + " damage dice";
			int damage = countDecisive(roll(attacker.initaitve));
			outcome += " for " + damage + " lethal damage!";
			System.out.println(outcome);
			attacker.resetToBaseInitiative();
			boolean killed = defender.loseHealth(damage);
			if(killed) {
				System.out.println(defender.name + " is dead!");
			}
			return killed;
		} else {
			outcome += " and misses! (" + attack + " vs " + defense +")";
			System.out.println(outcome);
			if(attacker.initaitve >= 11) {
				attacker.initaitve -= 3;
			} else {
				attacker.initaitve -=2;
			}
		}
		return false;
	}

}

package com.exalted.fight.attacks;

import static com.exalted.fight.Roller.roll;
import static com.exalted.fight.SuccessCounter.*;

import com.exalted.fight.Fighter;
import com.exalted.fight.Stunt;

public class DecisiveAttack implements Attack {

	@Override
	public boolean attack(Fighter attacker, Fighter defender) {
		Stunt attackStunt = Stunt.stunt();
		Stunt defenseStunt = Stunt.stunt();
		int attack = attackSuccesses(attacker, attackStunt);
		int defense = defender.defense(defenseStunt);
		defender.onslaught++;
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
			miss(attacker);
		}
		return false;
	}

	@Override
	public int attackSuccesses(Fighter attacker, Stunt stunt) {
		return attacker.decisiveAttack(stunt);
	}

	@Override
	public String name() {
		return "Decisive";
	}

	@Override
	public void miss(Fighter attacker) {
		if(attacker.initaitve >= 11) {
			attacker.initaitve -= 3;
		} else {
			attacker.initaitve -=2;
		}
	}

	@Override
	public boolean clashDamage(Fighter attacker, Fighter defender, int threshold, String outcome) {
		int damage = countDecisive(roll(attacker.initaitve)) + 1;
		outcome += " with " + attacker.initaitve + " damage dice for "+ damage + " lethal damage!";
		System.out.println(outcome);
		attacker.resetToBaseInitiative();
		defender.onslaught += 2;
		boolean killed = defender.loseHealth(damage);
		if(killed) {
			System.out.println(defender.name + " is dead!");
		}
		return killed;
	}

}

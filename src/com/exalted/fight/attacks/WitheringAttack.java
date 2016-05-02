package com.exalted.fight.attacks;

import com.exalted.fight.Fighter;
import com.exalted.fight.Stunt;

import static com.exalted.fight.Roller.roll;
import static com.exalted.fight.SuccessCounter.countIn;

public class WitheringAttack implements Attack {

	@Override
	public boolean attack(Fighter attacker, Fighter defender) {
		Stunt attackStunt = Stunt.stunt();
		Stunt defenseStunt = Stunt.stunt();
		int attack = attackSuccesses(attacker, attackStunt);
		int defense = defender.defense(defenseStunt);
		defender.onslaught++;
		String outcome = attacker.name + " makes a withering attack (" + attackStunt.display() + ") against " + defender.name + " (" + defenseStunt.display() +")";
		if(attack >= defense) {
			outcome += " and hits (" + attack + " vs " + defense +")";
			int threshold = attack - defense;
			int damageDice = Math.max(threshold + attacker.witheringDamage() - defender.witheringSoak(), attacker.weapon.overwhelming());
			outcome += " with " + damageDice + " damage dice";
			int damage = countIn(roll(damageDice));
			outcome += " for " + damage + " Initiative damage!";
			System.out.println(outcome);
			return applyWitheringDamage(attacker, defender, damage);
		} else {
			outcome += " and misses! (" + attack + " vs " + defense +")";
			System.out.println(outcome);
		}
		return false;
	}

	private boolean applyWitheringDamage(Fighter attacker, Fighter defender, int damage) {
		attacker.initaitve += (damage + 1);
		if(defender.loseInitative(damage)) {
			System.out.println(defender.name + " is crashed!");
			if(attacker.isCrashed()) {
				System.out.println("Initiative SHIFT!");
				attacker.crashCount = 0;
				attacker.initaitve = Math.max(attacker.initaitve, 3) + attacker.joinBattle(Stunt.stunt());
				System.out.println("BONUS ATTACK!");
				return attacker.attackAgainst(defender).attack(attacker, defender);
			} else {
				System.out.println("Initiative Break!");
				attacker.initaitve += 5;
			}
		}
		return false;
	}
	
	@Override
	public boolean clashDamage(Fighter attacker, Fighter defender, int threshold, String outcome) {
		int damageDice = Math.max(threshold + attacker.witheringDamage() - defender.witheringSoak(), attacker.weapon.overwhelming());
		int damage = countIn(roll(damageDice)) + 3;
		outcome += " with " + damageDice + " damage dice for "+ damage + " initiative damage!";
		System.out.println(outcome);
		defender.onslaught += 2;
		return applyWitheringDamage(attacker, defender, damage);
	}
	
	@Override
	public int attackSuccesses(Fighter attacker, Stunt stunt) {
		return attacker.witheringAttack(stunt);
	}

	@Override
	public String name() {
		return "Withering";
	}

	@Override
	public void miss(Fighter f1) {
	}

}

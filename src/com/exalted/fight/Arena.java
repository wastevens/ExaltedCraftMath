package com.exalted.fight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exalted.fight.strategies.KillingBlow;
import com.exalted.fight.strategies.ThousandCuts;

public class Arena {
	private static final int NUMBER_OF_FIGHTS = 10;
	private static final Fighter ALICE = new Fighter("Alice", 5, 5, 5, 5, 5, 5, Weapon.MORTAL_LIGHT, Armor.MORTAL_LIGHT, new KillingBlow());
	private static final Fighter BOB = new Fighter("Bob", 5, 5, 5, 5, 5, 5, Weapon.MORTAL_LIGHT, Armor.MORTAL_LIGHT, new ThousandCuts());

	public static void main(String a[]) {
		System.out.println("------------------");
		System.out.println(ALICE);
		System.out.println("------------------");
		System.out.println(BOB);
		System.out.println("------------------");
		Map<Fighter, Integer> scoreBoard = new HashMap<>();
		scoreBoard.put(ALICE, 0);
		scoreBoard.put(BOB, 0);
		for(int i=0;i<NUMBER_OF_FIGHTS;i++) {
			Fighter winner = fight();
			System.out.println("Fight " + (i+1) + " winner: " + winner.name);
			scoreBoard.put(winner, scoreBoard.get(winner) + 1);			
		}
		System.out.println("------------------");
		for (Fighter fighter : scoreBoard.keySet()) {
			System.out.println(fighter.name + " wins: " + scoreBoard.get(fighter));
		}
	}

	private static Fighter fight() {
		List<Fighter> fighters = new ArrayList<>();
		fighters.add(ALICE);
		fighters.add(BOB);
		fighters.forEach(f -> f.reset());
		fighters.forEach(f -> f.joinBattle(Stunt.stunt()));
		int round = 0;
		while(fighters.stream().anyMatch(f -> f.health > 0)) {
			++round;
			System.out.println("Round " + round);
			Collections.sort(fighters);
			if(resolve(fighters.get(0), fighters.get(1))) {
				return fighters.get(0);
			}
			if(resolve(fighters.get(1), fighters.get(0))) {
				return fighters.get(1);
			}
			cleanUp(fighters);
		}
		throw new IllegalStateException("The fight never ended?");
	}

	private static boolean resolve(Fighter attacker, Fighter defender) {
		boolean kill = attacker.attackAgainst(defender).attack(attacker, defender);
		if(kill) {
			System.out.println(attacker.name + " wins!");
		}
		return kill;
	}
	
	private static void cleanUp(Collection<Fighter> fighters) {
		for (Fighter fighter : fighters) {
			if(fighter.crashCount == 1) {
				fighter.crashCount = 0;
				fighter.resetToBaseInitiative();
				System.out.println(fighter.name + " has recovered from crash and reset to base intiative.");
			} else if(fighter.crashCount > 0){
				fighter.crashCount--;
			}
			
		}
	}
	
}

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
	private static final int NUMBER_OF_FIGHTS = 100;
	private static final String ALICE = "Alice";
	private static final String BOB = "Bob";

	public static void main(String a[]) {
		Map<String, Integer> scoreBoard = new HashMap<>();
		scoreBoard.put(ALICE, 0);
		scoreBoard.put(BOB, 0);
		for(int i=0;i<NUMBER_OF_FIGHTS;i++) {
			String winner = fight();
			scoreBoard.put(winner, scoreBoard.get(winner) + 1);			
		}
		System.out.println(scoreBoard);
	}

	private static String fight() {
		List<Fighter> fighters = new ArrayList<>();
		fighters.add(new Fighter(ALICE, 5, 5, 5, 5, 5, 5, Weapon.MORTAL_LIGHT, Armor.MORTAL_LIGHT, new KillingBlow()));
		fighters.add(new Fighter(BOB, 5, 5, 5, 5, 5, 5, Weapon.MORTAL_LIGHT, Armor.MORTAL_LIGHT, new ThousandCuts()));
		fighters.forEach(f -> f.joinBattle(Stunt.stunt()));
		while(fighters.stream().anyMatch(f -> f.health > 0)) {
			Collections.sort(fighters);
			if(resolve(fighters.get(0), fighters.get(1))) {
				return fighters.get(0).name;
			}
			if(resolve(fighters.get(1), fighters.get(0))) {
				return fighters.get(0).name;
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

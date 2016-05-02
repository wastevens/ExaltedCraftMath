package com.exalted.fight.strategies;

import com.exalted.fight.Fighter;
import com.exalted.fight.attacks.Attack;
import com.exalted.fight.attacks.DecisiveAttack;
import com.exalted.fight.attacks.WitheringAttack;

public class ThousandCuts implements Strategy {

	@Override
	public Attack toUse(Fighter attacker, Fighter defender) {
		if(attacker.initaitve > 3 && attacker.initaitve > defender.armor.hardness() && attacker.isCrashed() == false) {
			return new DecisiveAttack();
		}
		return new WitheringAttack();
	}
	
	@Override
	public String name() {
		return "Thousand Cuts";
	}

}

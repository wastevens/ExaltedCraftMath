package com.exalted.fight.strategies;

import com.exalted.fight.Fighter;
import com.exalted.fight.attacks.Attack;
import com.exalted.fight.attacks.DecisiveAttack;
import com.exalted.fight.attacks.WitheringAttack;

public class KillingBlow implements Strategy {

	@Override
	public Attack toUse(Fighter attacker, Fighter defender) {
		if(attacker.initaitve > defender.armor.hardness() && attacker.initaitve > (defender.health * 2) && attacker.isCrashed() == false) {
			return new DecisiveAttack();
		}
		return new WitheringAttack();
	}

	@Override
	public String name() {
		return "Killing Blow";
	}
}

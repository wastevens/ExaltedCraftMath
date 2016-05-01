package com.exalted.fight.strategies;

import com.exalted.fight.Attack;
import com.exalted.fight.DecisiveAttack;
import com.exalted.fight.Fighter;
import com.exalted.fight.WitheringAttack;

public class KillingBlow implements Strategy {

	@Override
	public Attack toUse(Fighter attacker, Fighter defender) {
		if(attacker.initaitve > defender.armor.hardness && attacker.initaitve > (defender.health * 2)) {
			return new DecisiveAttack();
		}
		return new WitheringAttack();
	}

}

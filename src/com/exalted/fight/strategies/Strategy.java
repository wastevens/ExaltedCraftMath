package com.exalted.fight.strategies;

import com.exalted.fight.Fighter;
import com.exalted.fight.attacks.Attack;

public interface Strategy {

	Attack toUse(Fighter attacker, Fighter defender);

	String name();
	
}

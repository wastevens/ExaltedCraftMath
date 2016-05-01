package com.exalted.fight.strategies;

import com.exalted.fight.Attack;
import com.exalted.fight.Fighter;

public interface Strategy {

	Attack toUse(Fighter attacker, Fighter defender);

	String name();
	
}

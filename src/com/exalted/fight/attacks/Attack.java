package com.exalted.fight.attacks;

import com.exalted.fight.Fighter;
import com.exalted.fight.Stunt;

public interface Attack {

	boolean attack(Fighter attacker, Fighter defender);
	
	int attackSuccesses(Fighter attacker, Stunt stunt);

	String name();

	void miss(Fighter f1);

	boolean clashDamage(Fighter attacker, Fighter defender, int threshold, String outcome);
}

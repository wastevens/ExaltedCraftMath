package com.exalted.fight;

import java.util.Collection;

public class SuccessCounter {

	public static int countIn(Collection<Integer> roll) {
		return Long.valueOf(roll.stream().filter(l -> l >= 10).count() + roll.stream().filter(l -> l >= 7).count()).intValue();
	}
	
	public static int countDecisive(Collection<Integer> roll) {
		return Long.valueOf(roll.stream().filter(l -> l >= 7).count()).intValue();
	}
}

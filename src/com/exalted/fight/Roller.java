package com.exalted.fight;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Roller {

	private static Random die = new Random();
	
	public static List<Integer> craft(int diceToRoll) {
		List<Integer> rolled = new ArrayList<Integer>();
		for(int i=0;i<diceToRoll;i++) {
			rolled.add(roll());
		}
		return rolled;
	}
	
	public static List<Integer> roll(int diceToRoll) {
		List<Integer> rolled = new ArrayList<Integer>();
		for(int i=0;i<diceToRoll;i++) {
			rolled.add(roll());
		}
//		System.out.println("Rolling " + diceToRoll + " " + rolled);
		return rolled;
	}
	
	public static int roll() {
		return die.nextInt(10)+1;
	}
	
	public static int rollDie(int max) {
		return die.nextInt(max)+1;
	}
	
}

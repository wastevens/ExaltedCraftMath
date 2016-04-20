import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class CrafterCindy {

	private static int ARTIFACT_2 = 30;
	private static int ARTIFACT_3 = 50;
	private static int ARTIFACT_4 = 75;
	private static int ARTIFACT_5 = 100;
	private static int DIFFICULTY = 5;
	private static int TERMINUS = 6;
	
	public static void main(String a[]) {
		int attempts = 1000;
		int successes = 0;
		ArrayList<Charm> charms = new ArrayList<Charm>();
		charms.add(new FlawlessHandiworkMethod_Essence3());
		charms.add(new SupremeMasterworkFocus_Essence2());
		charms.add(new ExperimentalConjourationOfTheVoid_Essence3());
		for(int i=0;i<attempts;i++) {
			if(craftAttempt(21, DIFFICULTY, TERMINUS, ARTIFACT_5, charms)) {
				successes++;
			}
		}
		
		BigDecimal percent = new BigDecimal(successes).divide(new BigDecimal(attempts)).multiply(new BigDecimal(100)).stripTrailingZeros();
		
		System.out.println(successes + " out of " + attempts + " successful (" + percent + "%)");
	}
	
	private static Predicate<Integer> successes(int target) {
		return (Integer i) -> i >= target;
	}
	
	private static boolean craftAttempt(int numberOfDice, int difficulty, int terminus, int goal, List<Charm> charms) {
		int totalSuccesses = 0;
		int excessSuccesses = 0;
		for(int i=0;i<terminus;i++) {
			List<Integer> roll = Roller.craft(numberOfDice);
			craftCharms(roll, charms);
			int successes = successesIn(roll, charms);
			totalSuccesses+= successes;
			excessSuccesses += Math.max((successes - difficulty), 0);
			if(excessSuccesses >= goal) {
				System.out.println("Succeded on action " + (i+1));
				break;
			}
		}
		System.out.println("Total Successes: " + totalSuccesses);
		System.out.println("Excess successes: " + excessSuccesses);
		System.out.println("Success? " + (excessSuccesses >= goal));
		return (excessSuccesses >= goal);
	}
	
	private static void craftCharms(List<Integer> roll, List<Charm> charms) {
		for (Charm charm : charms) {
			roll.addAll(charm.act(roll));
		}
	}

	private static int successesIn(List<Integer> rolled, List<Charm> charms) {
		int successes = 0;
		successes += rolled.stream().filter(successes(7)).count();
		successes += rolled.stream().filter(successes(10)).count();
		
		for (Charm charm : charms) {
			successes += charm.bonusSuccesses(rolled);
		}
		return successes;
	}
	
	private static class FlawlessHandiworkMethod_Essence1 implements Charm {

		@Override
		public List<Integer> act(List<Integer> rolled) {
			List<Integer> bonus = new ArrayList<Integer>();
			List<Integer> bonusRoll = new ArrayList<Integer>();
			long bonusRolls = rolled.stream().filter((Integer i) -> i == 10).count();
			while(bonusRolls > 0) {
				bonusRoll = Roller.craft((int) bonusRolls);
				bonus.addAll(bonusRoll);
				bonusRolls = bonusRoll.stream().filter((Integer i) -> i == 10).count();
			}
			return bonus;
		}

		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 0;
		}
	}
	
		private static class FlawlessHandiworkMethod_Essence3 implements Charm {
		
		@Override
		public List<Integer> act(List<Integer> rolled) {
			List<Integer> bonus = new ArrayList<Integer>();
			List<Integer> bonusRoll = new ArrayList<Integer>();
			long bonusRolls = rolled.stream().filter((Integer i) -> i == 10 || i == 6).count();
			while(bonusRolls > 0) {
				bonusRoll = Roller.craft((int) bonusRolls);
				bonus.addAll(bonusRoll);
				bonusRolls = bonusRoll.stream().filter((Integer i) -> i == 10 || i == 6).count();
			}
			return bonus;
		}
		
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 0;
		}
	}
	
	private static class ExperimentalConjourationOfTheVoid_Essence2 implements Charm {
		@Override
		public List<Integer> act(List<Integer> roll) {
			return Roller.craft(2);
		}

		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 1;
		}
	}
	
	private static class ExperimentalConjourationOfTheVoid_Essence3 implements Charm {
		@Override
		public List<Integer> act(List<Integer> roll) {
			return Roller.craft(8);
		}
		
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 1;
		}
	}
		
	private static class SupremeMasterworkFocus_Essence2 implements Charm {
		
		@Override
		public List<Integer> act(List<Integer> rolled) {
			return new ArrayList<Integer>();
		}

		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return (int) roll.stream().filter((Integer i) -> i == 9 || i == 8).count();
		}
	}
}

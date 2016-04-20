import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;


public class CraftScenario {

	private static int DIFFICULTY = 5;
	private static int TERMINUS = 6;
	
	public static void main(String a[]) {
		int attempts = 1000;
		int successes = 0;
		Crafter crafter = new SolarCrafter(Charms.ExperimentalConjourationOfTheVoid_Essence3,
				                           Charms.FlawlessHandiworkMethod_Essence3,
				                           Charms.SupremeMasterworkFocus_Essence2);
		for(int i=0;i<attempts;i++) {
			if(craftAttempt(crafter, Artifact.FIVE, DIFFICULTY, TERMINUS)) {
				successes++;
			}
		}
		
		BigDecimal percent = new BigDecimal(successes).divide(new BigDecimal(attempts)).multiply(new BigDecimal(100)).stripTrailingZeros();
		
		System.out.println(successes + " out of " + attempts + " successful (" + percent + "%)");
	}
	
	private static Predicate<Integer> successes(int target) {
		return (Integer i) -> i >= target;
	}
	
	private static boolean craftAttempt(Crafter crafter, ArtifactRating artifact, int difficulty, int terminus) {
		int totalSuccesses = 0;
		int excessSuccesses = 0;
		for(int i=0;i<terminus;i++) {
			List<Integer> roll = Roller.craft(crafter.getDicePool());
			craftCharms(roll, crafter.getCharms());
			int successes = successesIn(roll, crafter);
			totalSuccesses+= successes;
			excessSuccesses += Math.max((successes - difficulty), 0);
			if(excessSuccesses >= artifact.getGoalNumber()) {
				System.out.println("Succeded on action " + (i+1));
				break;
			}
		}
		System.out.println("Total Successes: " + totalSuccesses);
		System.out.println("Excess successes: " + excessSuccesses);
		System.out.println("Success? " + (excessSuccesses >= artifact.getGoalNumber()));
		return (excessSuccesses >= artifact.getGoalNumber());
	}
	
	private static void craftCharms(List<Integer> roll, List<Charm> charms) {
		for (Charm charm : charms) {
			roll.addAll(charm.act(roll));
		}
	}

	private static int successesIn(List<Integer> rolled, Crafter crafter) {
		int successes = 0;
		successes += rolled.stream().filter(successes(crafter.getTargetNumber())).count();
		successes += rolled.stream().filter(successes(crafter.getDoubleTargetNumber())).count();
		
		for (Charm charm : crafter.getCharms()) {
			successes += charm.bonusSuccesses(rolled);
		}
		return successes;
	}
}

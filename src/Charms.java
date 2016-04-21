import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public enum Charms implements Charm {

	FlawlessHandiworkMethod_Essence1 {
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
	},
	FlawlessHandiworkMethod_Essence3 {
		
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
	},
	SupremeMasterworkFocus_Essence2 {
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return (int) roll.stream().filter((Integer i) -> i == 9 || i == 8).count();
		}
	},
	ExperimentalConjourationOfTheVoid_Essence2 {
		@Override
		public List<Integer> act(List<Integer> roll) {
			return Roller.craft(2);
		}

		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 1;
		}
	},
	ExperimentalConjourationOfTheVoid_Essence3 {
		@Override
		public List<Integer> act(List<Integer> roll) {
			return Roller.craft(8);
		}
		
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 1;
		}
	},
	SublimeArtface_Essence2 {
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			long bonusFromFailures = 0;
			HashSet<Integer> rollValues = new HashSet<Integer>(roll);
			for (Integer rollValue : rollValues) {
				if(rollValue <= 3) {
					bonusFromFailures = Math.max(bonusFromFailures, roll.stream().filter((Integer i) -> i == rollValue).count());
				}
			}
			return (int) bonusFromFailures;
		}
	},
	SublimeArtface_Essence3 {
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			long bonusFromFailures = 0;
			long bonusFromSuccesses = 0;
			HashSet<Integer> rollValues = new HashSet<Integer>(roll);
			for (Integer rollValue : rollValues) {
				if(rollValue <= 3) {
					bonusFromFailures = Math.max(bonusFromFailures, roll.stream().filter((Integer i) -> i == rollValue).count());
				}
				if(rollValue >= 4) {
					bonusFromSuccesses = Math.max(bonusFromSuccesses, roll.stream().filter((Integer i) -> i == rollValue).count());
				}
			}
			return (int) (bonusFromFailures + bonusFromSuccesses);
		}
	},
	TimeEnough_Essence2 {
		@Override
		public int getBonusToTerminus() {
			return 1;
		}
	},
	TimeEnough_Essence3 {
		@Override
		public int getBonusToTerminus() {
			return 2;
		}
	},
	Crafting_Arts {
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 1;
		}
	}
}

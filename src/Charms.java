import java.util.ArrayList;
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

		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 0;
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
		
		@Override
		public int bonusSuccesses(List<Integer> roll) {
			return 0;
		}
	},
	SupremeMasterworkFocus_Essence2 {
		
		@Override
		public List<Integer> act(List<Integer> rolled) {
			return new ArrayList<Integer>();
		}

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
	}
	
}

import java.util.ArrayList;
import java.util.List;


public interface Charm {
	
	default List<Integer> act(List<Integer> roll) {
		return new ArrayList<Integer>();
	}
	default int bonusSuccesses(List<Integer> roll) {
		return 0;
	}
	default int getBonusToTerminus() {
		return 0;
	};
	
}

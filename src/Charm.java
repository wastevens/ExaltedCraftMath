import java.util.List;


public interface Charm {
	
	List<Integer> act(List<Integer> roll);
	int bonusSuccesses(List<Integer> roll);
	
}

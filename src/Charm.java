import java.util.List;


public interface Charm {

	public List<Integer> act(List<Integer> roll);
	public int bonusSuccesses(List<Integer> roll);
	
}

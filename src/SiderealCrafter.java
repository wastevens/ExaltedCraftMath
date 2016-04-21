import java.util.Arrays;
import java.util.List;


public class SiderealCrafter implements Crafter {

	private final List<Charm> charms;
	private final int essence;

	public SiderealCrafter(int essence, Charm... charms) {
		this.essence = essence;
		this.charms = Arrays.asList(charms);
	}
	
	@Override
	public List<Charm> getCharms() {
		return charms;
	}

	@Override
	public int getDicePool() {
		return 11 + essence +1; //stunt!
	}

	@Override
	public int getTargetNumber() {
		return 4;
	}

	@Override
	public int getDoubleTargetNumber() {
		return 10;
	}

}

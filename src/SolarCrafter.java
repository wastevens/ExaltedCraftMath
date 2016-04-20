import java.util.Arrays;
import java.util.List;


public class SolarCrafter implements Crafter {

	private final List<Charm> charms;

	public SolarCrafter(Charm... charms) {
		this.charms = Arrays.asList(charms);
	}
	
	@Override
	public List<Charm> getCharms() {
		return charms;
	}

	@Override
	public int getDicePool() {
		return 21;
	}

	@Override
	public int getTargetNumber() {
		return 7;
	}

	@Override
	public int getDoubleTargetNumber() {
		return 10;
	}

}

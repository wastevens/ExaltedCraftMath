
public enum Artifact implements ArtifactRating {
	TWO(30),
	THREE(50),
	FOUR(75),
	FIVE(100),
	LEGENDARY(200)
	;
	
	private final int goalNumber;

	private Artifact(int goalNumber) {
		this.goalNumber = goalNumber;
	}
	
	@Override
	public int getGoalNumber() {
		return goalNumber;
	}
	
}
